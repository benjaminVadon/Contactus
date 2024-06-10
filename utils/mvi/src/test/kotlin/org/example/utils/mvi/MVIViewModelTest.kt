package org.example.utils.mvi

import org.example.utils.mvi.contract.Action
import org.example.utils.mvi.contract.Event
import org.example.utils.mvi.contract.State
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MVIViewModelTest {

    private lateinit var viewModel: TestMVIViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TestMVIViewModel(TestState())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `updateUiState updates state correctly`() = runTest(testDispatcher) {
        val expectedState = TestState(count = 1)
        viewModel.updateUiStateTest { copy(count = count + 1) }
        assertEquals(expectedState, viewModel.uiStateFlow.value)
    }

    @Test
    fun `emit sends action correctly`() = runTest(testDispatcher) {
        val action = TestAction.Increment
        launch { viewModel.emitTest(action) }
        val emittedAction = viewModel.actionFlow.first()
        assertEquals(action, emittedAction)
    }

    @Test
    fun `emit sends action correctly on right dispatcher`() = runTest(testDispatcher) {
        val action = TestAction.Increment
        viewModel.emitTest(testDispatcher, action)
        val emittedAction = viewModel.actionFlow.first()
        assertEquals(action, emittedAction)
    }

    @Test
    fun `launch with dispatcher executes block on specified dispatcher`() =
        runTest(testDispatcher) {
            var executedOnDefaultDispatcher = false
            viewModel.launchTest(Dispatchers.Default) {
                executedOnDefaultDispatcher = true
            }.join()
            advanceUntilIdle()
            assertEquals(true, executedOnDefaultDispatcher)
        }

    @Test
    fun `collect and update state in the same time`() = runTest(testDispatcher) {
        val expectedState = TestState(count = 1)
        with(viewModel) {
            val job = launch {
                testFlow.collectUpdateStateTest {
                    copy(count = count + 1)
                }
            }
            launch {
                testFlow.emit(true)
                job.cancel()
            }.join()
            assertEquals(expectedState, uiStateFlow.value)
        }
    }

    data class TestState(val count: Int = 0) : State
    sealed class TestEvent : Event {
        data object Increment : TestEvent()
    }

    sealed class TestAction : Action {
        data object Increment : TestAction()
    }

    class TestMVIViewModel(initialState: TestState) :
        MVI<TestState, TestEvent, TestAction>(initialState) {
        val testFlow = MutableSharedFlow<Boolean>()
        override fun process(event: TestEvent) {
            when (event) {
                TestEvent.Increment -> updateUiState { copy(count = count + 1) }
            }
        }

        fun launchTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Unit) =
            launch(dispatcher, block)

        fun updateUiStateTest(block: TestState.() -> TestState) = updateUiState(block)
        suspend fun emitTest(action: TestAction) = emit(action)
        fun emitTest(dispatcher: CoroutineDispatcher, action: TestAction) = emit(dispatcher, action)
        suspend fun <T> Flow<T>.collectUpdateStateTest(
            stateUpdater: TestState.(T) -> TestState
        ) = collectUpdateState(stateUpdater)

    }

}
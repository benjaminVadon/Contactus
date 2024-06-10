package org.example.domain.connectivity

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.example.data.connectivity.ConnectivityObserver
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WaitConnectionUseCaseTest {
    private val connectivityObserver = mockk<ConnectivityObserver>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var scope: CoroutineScope
    private lateinit var useCase: WaitConnectionUseCase

    @Before
    fun setup() {
        scope = CoroutineScope(testDispatcher)
        useCase = WaitConnectionUseCase(connectivityObserver, scope)
    }

    @Test
    fun `invoke waits for connection when initially disconnected`() = runTest(testDispatcher) {
        var isWaiting = true
        val mockFlow = MutableStateFlow(false)
        every { connectivityObserver.isConnected } returns mockFlow
        launch {
            useCase()
            isWaiting = false
        }
        assertTrue(isWaiting)
        mockFlow.value = true
        testDispatcher.scheduler.advanceUntilIdle()
        assertFalse(isWaiting)
    }

    @Test
    fun `invoke does not wait when already connected`() = runTest {
        val mockFlow = MutableStateFlow(true)
        every { connectivityObserver.isConnected } returns mockFlow
        scope.launch {
            useCase()
            assertTrue(connectivityObserver.isConnected.value)
        }
    }
}
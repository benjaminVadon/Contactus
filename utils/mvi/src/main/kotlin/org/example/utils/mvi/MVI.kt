package org.example.utils.mvi

import androidx.annotation.AnyThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.utils.mvi.contract.MVIAction
import org.example.utils.mvi.contract.MVIEvent
import org.example.utils.mvi.contract.MVIState

abstract class MVI<S : MVIState, E : MVIEvent, A : MVIAction>(initialState: S) : ViewModel() {
    private val localUiStateFlow = MutableStateFlow(initialState)
    private val localActionFlow = MutableSharedFlow<A>()

    val uiStateFlow = localUiStateFlow.asStateFlow()
    val actionFlow = localActionFlow.asSharedFlow()

    abstract fun process(event: E)

    @AnyThread
    protected fun updateUiState(block: S.() -> S): Unit = localUiStateFlow.update { it.block() }

    @WorkerThread
    protected suspend fun emit(action: A): Unit = localActionFlow.emit(action)

    @AnyThread
    protected fun emit(
        dispatcher: CoroutineDispatcher,
        action: A,
    ) {
        launch(dispatcher) {
            emit(action)
        }
    }

    @WorkerThread
    protected suspend fun <T> Flow<T>.collectUpdateState(
        stateUpdater: S.(T) -> S
    ): Unit = collect { updateUiState { stateUpdater(it) } }

    @AnyThread
    protected fun launch(
        dispatcher: CoroutineDispatcher,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(context = dispatcher, block = block)
}
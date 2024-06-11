package org.example.contactus.mainActivityContent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.example.contactus.mainActivityContent.contract.MainActions
import org.example.contactus.mainActivityContent.contract.MainEvents
import org.example.contactus.mainActivityContent.contract.MainState
import org.example.domain.connectivity.IsInternetConnectedUseCase
import org.example.utils.coroutines.dispatchers.AppDispatchers.Default
import org.example.utils.coroutines.dispatchers.Dispatcher
import org.example.utils.mvi.MVI
import javax.inject.Inject

@HiltViewModel
internal class MainActivityContentViewModel @Inject constructor(
    private val isInternetConnectedUseCase: IsInternetConnectedUseCase,
    @Dispatcher(Default) private val dispatcher: CoroutineDispatcher
) : MVI<MainState, MainEvents, MainActions>(MainState()) {
    override fun process(event: MainEvents): Unit = when (event) {
        else -> {}
    }

    init {
        observeInternetConnection()
    }

    private fun observeInternetConnection() {
        launch(dispatcher) {
            isInternetConnectedUseCase().collectUpdateState { isConnected ->
                onInternetConnectionChanged(isConnected)
            }
        }
    }
}

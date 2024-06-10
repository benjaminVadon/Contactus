package org.example.domain.connectivity

import kotlinx.coroutines.flow.StateFlow
import org.example.data.connectivity.ConnectivityObserver
import javax.inject.Inject

class IsInternetConnectedUseCase @Inject constructor(
    private val connectionObserver: ConnectivityObserver,
) {
    operator fun invoke(): StateFlow<Boolean> = connectionObserver.isConnected
}
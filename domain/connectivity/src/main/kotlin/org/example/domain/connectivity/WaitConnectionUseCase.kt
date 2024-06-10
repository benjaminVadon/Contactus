package org.example.domain.connectivity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.data.connectivity.ConnectivityObserver
import org.example.utils.coroutines.scopes.AppCoroutineScopes.Default
import org.example.utils.coroutines.scopes.Scope
import javax.inject.Inject

class WaitConnectionUseCase @Inject constructor(
    private val connectionObserver: ConnectivityObserver,
    @Scope(Default) private val scope: CoroutineScope,
) {
    private val lock = Object()
    private var connectionWaiterJob: Job? = null

    operator fun invoke(): Unit = runBlocking {
        synchronized(lock) {
            connectionWaiterJob ?: startConnectionWaiterIfNeeded()
        }?.join()
    }

    private fun startConnectionWaiterIfNeeded(): Job? =
        connectionObserver.isConnected
            .takeUnless { it.value }
            ?.let { startConnectionWaiter() }

    private fun startConnectionWaiter(): Job = scope.launch {
        connectionObserver.isConnected.collect { isConnected ->
            if (isConnected) {
                connectionWaiterJob?.cancel()
                connectionWaiterJob = null
            }
        }
    }.also { connectionWaiterJob = it }
}
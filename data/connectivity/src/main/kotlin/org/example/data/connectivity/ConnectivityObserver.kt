package org.example.data.connectivity

import android.app.Application
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import org.example.utils.coroutines.scopes.AppCoroutineScopes.*
import org.example.utils.coroutines.scopes.Scope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityObserver @Inject constructor(
    application: Application,
    @Scope(Default) private val scope: CoroutineScope,
) {
    private val connectivityManager: ConnectivityManager? = application.getSystemService()
    private val localIsConnected = MutableStateFlow(false)
    internal val networkCallback by lazy {
        ConnectionChangeCallback(connectivityManager) { isConnected ->
            localIsConnected.value = isConnected
        }
    }

    val isConnected: StateFlow<Boolean> =
        localIsConnected.asStateFlow()

    init {
        connectivityManager?.run {
            createConnectivityFlow().launchIn(scope)
        }
    }

    private fun ConnectivityManager.createConnectivityFlow(): Flow<Unit> = callbackFlow {
        registerDefaultNetworkCallback(networkCallback)
        awaitClose { unregisterNetworkCallback(networkCallback) }
    }
}
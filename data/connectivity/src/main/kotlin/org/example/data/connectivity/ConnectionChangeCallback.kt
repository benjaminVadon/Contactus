package org.example.data.connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import org.example.data.connectivity.extensions.hasInternetAccess

class ConnectionChangeCallback(
    private val connectivityManager: ConnectivityManager?,
    private val onConnectivityChanged: (isConnected: Boolean) -> Unit
) : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network): Unit =
        onConnectivityChanged(network.hasInternetAccess(connectivityManager))

    override fun onCapabilitiesChanged(
        network: Network,
        networkCapabilities: NetworkCapabilities
    ): Unit = onConnectivityChanged(networkCapabilities.hasInternetAccess())

    override fun onLost(network: Network): Unit = onConnectivityChanged(false)
}
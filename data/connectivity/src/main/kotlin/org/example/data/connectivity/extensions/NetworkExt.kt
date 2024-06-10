package org.example.data.connectivity.extensions

import android.net.ConnectivityManager
import android.net.Network

fun Network?.hasInternetAccess(
    connectivityManager: ConnectivityManager?
) = connectivityManager
    ?.getNetworkCapabilities(this)
    ?.hasInternetAccess()
    ?: false
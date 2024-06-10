package org.example.data.connectivity.extensions

import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_VPN
import android.net.NetworkCapabilities.TRANSPORT_WIFI


internal fun NetworkCapabilities.hasInternetAccess() =
    hasInternetCapabilities() && hasDataTransport()

internal fun NetworkCapabilities.hasInternetCapabilities(): Boolean = hasAllCapabilities(
    NET_CAPABILITY_INTERNET,
    NET_CAPABILITY_VALIDATED
)

internal fun NetworkCapabilities.hasDataTransport(): Boolean = hasTransportIn(
    TRANSPORT_WIFI,
    TRANSPORT_VPN,
    TRANSPORT_CELLULAR,
    TRANSPORT_ETHERNET
)

@Suppress("SameParameterValue")
private fun NetworkCapabilities.hasAllCapabilities(
    vararg netCapability: Int
): Boolean = netCapability.all(::hasCapability)

@Suppress("SameParameterValue")
private fun NetworkCapabilities.hasTransportIn(
    vararg transportType: Int
): Boolean = transportType.any(::hasTransport)
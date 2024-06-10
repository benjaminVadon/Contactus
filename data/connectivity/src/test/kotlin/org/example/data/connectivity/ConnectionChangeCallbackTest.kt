package org.example.data.connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ConnectionChangeCallbackTest {

    private val onConnectivityChanged = mockk<(Boolean) -> Unit>(relaxed = true)
    private val connectivityManager = mockk<ConnectivityManager>()
    private val network = mockk<Network>()
    private val networkCapabilities = mockk<NetworkCapabilities>()

    @Test
    fun `onAvailable with internet`() {
        val callback = ConnectionChangeCallback(connectivityManager, onConnectivityChanged)
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasCapability(any()) } answers { true }
        every { networkCapabilities.hasTransport(any()) } answers { true }
        callback.onAvailable(network)
        verify { onConnectivityChanged(true) }
    }

    @Test
    fun `onAvailable without internet`() {
        val callback = ConnectionChangeCallback(connectivityManager, onConnectivityChanged)
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasCapability(any()) } answers { false }
        every { networkCapabilities.hasTransport(any()) } answers { false }
        callback.onAvailable(network)
        verify { onConnectivityChanged(false) }
    }


    @Test
    fun `onCapabilitiesChanged with internet`() {
        val callback = ConnectionChangeCallback(connectivityManager, onConnectivityChanged)
        every { networkCapabilities.hasCapability(any()) } answers { true }
        every { networkCapabilities.hasTransport(any()) } answers { true }
        callback.onCapabilitiesChanged(mockk(), networkCapabilities)
        verify { onConnectivityChanged(true) }
    }

    @Test
    fun `onCapabilitiesChanged without internet`() {
        val callback = ConnectionChangeCallback(connectivityManager, onConnectivityChanged)
        every { networkCapabilities.hasCapability(any()) } answers { false }
        every { networkCapabilities.hasTransport(any()) } answers { false }
        callback.onCapabilitiesChanged(mockk(), networkCapabilities)
        verify { onConnectivityChanged(false) }
    }


    @Test
    fun `onLost calls onConnectivityChanged with false`() {
        val callback = ConnectionChangeCallback(null, onConnectivityChanged)
        callback.onLost(mockk())
        verify { onConnectivityChanged(false) }
    }
}
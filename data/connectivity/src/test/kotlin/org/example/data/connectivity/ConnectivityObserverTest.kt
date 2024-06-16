package org.example.data.connectivity

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ConnectivityObserverTest {

    private val application = mockk<Application>()
    private val connectivityManager = mockk<ConnectivityManager>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var scope: CoroutineScope

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        scope = CoroutineScope(testDispatcher)
        every { application.getSystemService<ConnectivityManager>() } returns connectivityManager
    }

    @Test
    fun `isConnected is initially false`() = runTest {
        every { connectivityManager.registerDefaultNetworkCallback(any()) } just runs
        val isConnected = ConnectivityObserver(application, scope).isConnected.first()
        assertFalse(isConnected)
    }

    @Test
    fun `network callback is registered after initialization`() = runTest(testDispatcher) {
        val callbackSlot = slot<ConnectionChangeCallback>()
        every { connectivityManager.registerDefaultNetworkCallback(capture(callbackSlot)) } just runs
        val observer = ConnectivityObserver(application, scope)
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(observer.networkCallback, callbackSlot.captured)
    }

    @Test
    fun `connectivity change updates isConnected flow`() = runTest {
        val networkCapabilities = mockk<NetworkCapabilities>()
        val connectivityObserver = ConnectivityObserver(application, scope)
        every { connectivityManager.registerDefaultNetworkCallback(any()) } just runs
        every { connectivityManager.getNetworkCapabilities(any()) } returns networkCapabilities
        every { networkCapabilities.hasCapability(any()) } answers { true }
        every { networkCapabilities.hasTransport(any()) } answers { true }

        val callback = connectivityObserver.networkCallback

        callback.onAvailable(mockk())
        callback.onCapabilitiesChanged(mockk(), networkCapabilities)
        assertTrue(connectivityObserver.isConnected.first())

        callback.onLost(mockk())
        assertFalse(connectivityObserver.isConnected.first())
    }
}
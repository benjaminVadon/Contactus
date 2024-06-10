package org.example.domain.connectivity

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.example.data.connectivity.ConnectivityObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class IsInternetConnectedUseCaseTest {

    private val connectivityObserver = mockk<ConnectivityObserver>()
    private lateinit var useCase: IsInternetConnectedUseCase

    @Before
    fun setup() {
        useCase = IsInternetConnectedUseCase(connectivityObserver)
    }

    @Test
    fun `invoke returns the isConnected flow from ConnectivityObserver`() = runTest {
        val mockFlow = MutableStateFlow(false)
        every { connectivityObserver.isConnected } returns mockFlow
        val resultFlow = useCase()
        assertEquals(mockFlow, resultFlow)
    }
}
package org.example.data.network.http


import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.example.domain.connectivity.WaitConnectionUseCase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class WaitConnectionInterceptorTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var waitConnectionUseCase: WaitConnectionUseCase
    private lateinit var interceptor: WaitConnectionInterceptor
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        waitConnectionUseCase = mockk(relaxed = true)
        interceptor = WaitConnectionInterceptor(waitConnectionUseCase)
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulRequest() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK))

        val request = okhttp3.Request.Builder()
            .url(mockWebServer.url("/"))
            .build()

        val response = okHttpClient.newCall(request).execute()

        assertEquals(HttpURLConnection.HTTP_OK, response.code)
        verify(exactly = 1) { waitConnectionUseCase() }
    }

    @Test
    fun testRetryAndSuccess() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR))
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR))
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK))

        val request = okhttp3.Request.Builder()
            .url(mockWebServer.url("/"))
            .build()

        val response = okHttpClient.newCall(request).execute()

        assertEquals(HttpURLConnection.HTTP_OK, response.code)
        verify(exactly = 3) { waitConnectionUseCase() }
    }

    @Test
    fun testMaxRetriesExceeded() = runBlocking {
        repeat(4) {
            mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR))
        }

        val request = okhttp3.Request.Builder()
            .url(mockWebServer.url("/"))
            .build()

        val response = okHttpClient.newCall(request).execute()

        assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, response.code)
        verify(exactly = 4) { waitConnectionUseCase() }
    }
}
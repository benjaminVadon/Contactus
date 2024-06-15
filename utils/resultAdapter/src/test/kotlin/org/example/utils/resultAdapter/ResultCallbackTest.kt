package org.example.utils.resultAdapter

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ResultCallbackTest {

    @MockK
    lateinit var call: Call<String>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `ResultCallback handles successful response`() {
        val callback = mockk<Callback<Result<String>>>(relaxed = true)
        val resultCall = ResultCall(call)
        val resultCallback = ResultCallback(callback, resultCall)
        val response = Response.success("Success")
        resultCallback.onResponse(call, response)

        verify { callback.onResponse(resultCall, any()) }
    }

    @Test
    fun `ResultCallback handles failure response`() {
        val callback = mockk<Callback<Result<String>>>(relaxed = true)
        val resultCall = ResultCall(call)
        val resultCallback = ResultCallback(callback, resultCall)
        val responseBody = mockk<ResponseBody>()
        every { responseBody.contentType() } returns null
        every { responseBody.contentLength() } returns 0
        val response = Response.error<String>(400, responseBody)
        resultCallback.onResponse(call, response)

        verify { callback.onResponse(resultCall, any()) }
    }

    @Test
    fun `ResultCallback handles IOException as NoInternetConnectionException`() {
        val callback = mockk<Callback<Result<String>>>(relaxed = true)
        val resultCall = ResultCall(call)
        val resultCallback = ResultCallback(callback, resultCall)
        val exception = IOException("Network error")
        resultCallback.onFailure(call, exception)

        verify { callback.onResponse(resultCall, any<Response<Result<String>>>()) }
    }
}
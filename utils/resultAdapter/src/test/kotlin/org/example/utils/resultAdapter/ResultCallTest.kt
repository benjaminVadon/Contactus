package org.example.utils.resultAdapter

import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultCallTest {

    @MockK
    lateinit var call: Call<String>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `ResultCall enqueues ResultCallback`() {
        val relaxedCall = mockk<Call<Result<String>>>(relaxed = true)
        val resultCall = ResultCall(relaxedCall)
        val callbackSlot = slot<Callback<Result<String>>>()
        every { relaxedCall.enqueue(capture(callbackSlot)) } just Runs
        resultCall.enqueue(mockk())

        assert(callbackSlot.captured is ResultCallback)
    }

    @Test
    fun `ResultCall delegates execution to original call`() {
        val resultCall = ResultCall(call)
        val response = Response.success("Success")
        every { call.execute() } returns response

        val resultResponse = resultCall.execute()
        assert(resultResponse.body() == Result.success("Success"))
    }
}
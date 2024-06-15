package org.example.utils.resultAdapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultCall<T>(private val delegate: Call<T>) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) =
        delegate.enqueue(ResultCallback(callback, this))

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun execute(): Response<Result<T>> =
        Response.success(Result.success(delegate.execute().body()!!))

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun clone(): Call<Result<T>> = ResultCall(delegate.clone())

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
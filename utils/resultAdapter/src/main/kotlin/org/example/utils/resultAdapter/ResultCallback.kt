package org.example.utils.resultAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ResultCallback<T>(
    private val callback: Callback<Result<T>>,
    private val resultCall: ResultCall<T>
) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful)
            callback.onResponse(resultCall, toSuccess(response))
        else
            callback.onResponse(resultCall, toFailure(HttpException(response)))
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        val error = when (t) {
            is IOException -> NoInternetConnectionException(t)
            else -> RuntimeException(t.localizedMessage)
        }
        callback.onResponse(resultCall, toFailure(error))
    }

    private fun <T> toSuccess(response: Response<T>): Response<Result<T>> = with(response) {
        Response.success(code(), Result.success(body()!!))
    }

    private fun <T> toFailure(exception: Exception): Response<Result<T>> =
        Response.success(Result.failure(exception))
}
package org.example.utils.resultAdapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultCallAdapter(private val type: Type) : CallAdapter<Any, Call<Result<*>>> {
    override fun responseType(): Type = type

    @Suppress("UNCHECKED_CAST")
    override fun adapt(call: Call<Any>): Call<Result<*>> = ResultCall(call) as Call<Result<*>>
}
package org.example.utils.resultAdapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

class ResultCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? =
        returnType.instance<ParameterizedType>()
            ?.takeIf(::isCall)
            ?.let { getParameterUpperBound(0, it) }
            ?.instance<ParameterizedType>()
            ?.takeIf(::isResult)
            ?.let { ResultCallAdapter(getParameterUpperBound(0, it)) }

    private inline fun <reified T> Any.instance(): T? = if (this is T) this else null

    private fun isResult(type: ParameterizedType) =
        type.rawType == Result::class.java

    private fun isCall(type: ParameterizedType) =
        type.rawType == Call::class.java
}

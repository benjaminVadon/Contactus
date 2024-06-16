package org.example.data.network.http

import okhttp3.Interceptor
import okhttp3.Response
import org.example.domain.connectivity.WaitConnectionUseCase
import javax.inject.Inject

class WaitConnectionInterceptor @Inject constructor(
    private val waitConnectionUseCase: WaitConnectionUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        waitConnectionUseCase()
        var response = chain.proceed(request)
        var tryCount = 0
        while (!response.isSuccessful && tryCount < MAX_RETRY) {
            response.close()
            tryCount++
            request = request.newBuilder().build()
            waitConnectionUseCase()
            response = chain.proceed(request)
        }
        return response
    }

    companion object {
        private const val MAX_RETRY = 3
    }
}
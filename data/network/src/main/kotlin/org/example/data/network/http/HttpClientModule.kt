package org.example.data.network.http

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Provides
    fun providesHttpClient(
        waitConnectionInterceptor: WaitConnectionInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
        .addInterceptor(waitConnectionInterceptor)
        .build()

}
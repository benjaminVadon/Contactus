package org.example.data.network.http

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.example.utils.resultAdapter.ResultCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val baseApiUrl = "https://randomuser.me/"
    private val gson = GsonBuilder().create()

    @Provides
    fun providesRetrofit(
        httpClient: OkHttpClient,
        resultCallAdapterFactory: ResultCallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseApiUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(resultCallAdapterFactory)
        .build()
}
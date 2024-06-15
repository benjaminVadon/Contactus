package org.example.data.network.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object ContactApiModule {
    @Provides
    fun provide(
        retrofit: Retrofit
    ): ContactApi = retrofit.create()
}
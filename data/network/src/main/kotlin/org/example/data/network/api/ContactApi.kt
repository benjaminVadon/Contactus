package org.example.data.network.api

import org.example.data.network.model.ContactsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApi {
    @GET("api/")
    suspend fun getContacts(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String = "yeah",
    ): Result<ContactsResponse>
}
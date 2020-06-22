package com.platformstest.data.services
import com.platformstest.data.models.FetcherModelItem
import retrofit2.http.GET


interface RestDataService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getData(): FetcherModelItem
}
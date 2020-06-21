package com.platformstest.data.services
import com.platformstest.data.models.FetcherModelItem
import retrofit2.http.GET


interface RestDataService {
    @GET("repositories")
    suspend fun getUserData(): List<FetcherModelItem>
}
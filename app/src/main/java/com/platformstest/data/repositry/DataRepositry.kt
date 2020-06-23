package com.platformstest.data.repositry
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.services.RestDataService
import javax.inject.Inject

//Repositry class with service injected
class DataRepositry @Inject constructor(private val mService: RestDataService) {
    suspend fun getData(): FetcherModelItem = mService.getData()
}
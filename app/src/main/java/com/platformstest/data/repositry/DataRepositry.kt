package com.platformstest.data.repositry
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.services.LocalDataServiceDao
import com.platformstest.data.services.RestDataService
import javax.inject.Inject

class DataRepositry @Inject constructor(private val mService: RestDataService,
                                        private val mDao: LocalDataServiceDao) {
    suspend fun getUserData(): List<FetcherModelItem> = mService.getUserData()
    suspend fun insertUserData(userResponseData: List<FetcherModelItem>) = mDao.insertPeopleList(userResponseData)
}
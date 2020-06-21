package com.platformstest.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.platformstest.common.*
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.repositry.DataRepositry
import com.platformstest.data.services.LocalDataServiceDao
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class FetcherViewModel @Inject constructor(
    private val dataRepository: DataRepositry,
    private val mDao: LocalDataServiceDao
) : BaseViewModel() {

    private var mGetPostData: MutableLiveData<ViewState<List<FetcherModelItem>>> = MutableLiveData()

    fun getPostLiveData(): MutableLiveData<ViewState<List<FetcherModelItem>>> {
        return mGetPostData
    }

    private fun onError(throwable: Throwable) {
        mGetPostData.value = NetworkError(throwable.message)
    }

    fun getServerPost() {
        mGetPostData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Get User Post Data
            val userResponseData = dataRepository.getUserData()
            // Return the result on main thread via Dispatchers.Main
            if (userResponseData != null) {
                try {
                    dataRepository.insertUserData(userResponseData)
                } catch (e: Exception) {
                }
                mGetPostData.value = Success(userResponseData)
            } else {
                mGetPostData.value = NetworkError("Not a valid response")
            }
        }
    }

    fun getLocalCachePost() {
        mGetPostData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Get User Post Data
            val userResponseData = mDao.getPeopleList()
            // Return the result on main thread via Dispatchers.Main
            if (userResponseData != null) {
                mGetPostData.value = Success(userResponseData)
            } else {
                mGetPostData.value = NetworkError("Not a valid response")
            }
        }
    }

}
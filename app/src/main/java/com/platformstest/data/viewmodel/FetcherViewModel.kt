package com.platformstest.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.platformstest.common.*
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.repositry.DataRepositry
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

//Viewmodel class for making api call and returning data to ui
class FetcherViewModel @Inject constructor(
    private val dataRepository: DataRepositry
) : BaseViewModel() {

    private var mGetPostData: MutableLiveData<ViewState<FetcherModelItem>> = MutableLiveData()

    fun getPostLiveData(): MutableLiveData<ViewState<FetcherModelItem>> {
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
            // Get Post Data
            val userResponseData = dataRepository.getData()
            // Return the result on main thread via Dispatchers.Main
            if (userResponseData != null) {
                mGetPostData.value = Success(userResponseData)
            } else {
                mGetPostData.value = NetworkError("Not a valid response")
            }
        }
    }

}
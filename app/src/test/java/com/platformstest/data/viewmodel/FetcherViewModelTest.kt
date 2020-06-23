package com.platformstest.data.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.platformstest.common.Loading
import com.platformstest.common.NetworkError
import com.platformstest.common.Success
import com.platformstest.common.ViewState
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.models.Row
import com.platformstest.data.repositry.DataRepositry
import com.platformstest.data.services.RestDataService
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@RunWith(JUnit4::class)
class FetcherViewModelTest {

    @get:Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var dataRepositry: DataRepositry

    @Mock
    private lateinit var context: Application

    @Mock
    @Inject
    lateinit var mService: RestDataService

    @Mock
    private lateinit var mockObserver: Observer<ViewState<FetcherModelItem>>

    private lateinit var mViewModel: FetcherViewModel

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderMuseumsObserver: Observer<FetcherModelItem>

    private lateinit var mEmptyList: FetcherModelItem
    private lateinit var mList: FetcherModelItem
    private var mGetPostData: MutableLiveData<ViewState<FetcherModelItem>> = MutableLiveData()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        dataRepositry = DataRepositry(mService = mService)
        mViewModel = FetcherViewModel(dataRepositry)
        mockData()
        setupObservers()
    }

    @Test
    fun `test_api`() {
        // Mock Response
        mViewModel.getPostLiveData().postValue(Loading)
        mViewModel.getServerPost()
        mViewModel.getPostLiveData().observeForever(Observer { onData(it) })
    }

    private fun onData(viewState: ViewState<FetcherModelItem>?) {
        when (viewState) {
            is Loading -> {

            }
            is NetworkError -> {

            }
            is Success -> {
                Assert.assertNotNull(viewState.mData)
            }
        }
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderMuseumsObserver = mock(Observer::class.java) as Observer<FetcherModelItem>
    }

    private fun mockData() {
        val mockList: MutableList<Row> = mutableListOf()
        mockList.add(Row("a", "a", "a"))
        mockList.add(Row("b", "b", "b"))
        mockList.add(Row("c", "c", "c"))
        mEmptyList = FetcherModelItem(mockList, "")
    }
}
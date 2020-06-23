package com.platformstest.data.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.platformstest.common.Loading
import com.platformstest.common.NetworkError
import com.platformstest.common.Success
import com.platformstest.common.ViewState
import com.platformstest.data.models.FetcherModelItem
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
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import javax.inject.Inject

@RunWith(JUnit4::class)
class FetcherViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var dataRepositry: DataRepositry

    @Mock
    @Inject
    lateinit var mService: RestDataService

    @Mock
    private lateinit var mockObserver: Observer<ViewState<FetcherModelItem>>

    private lateinit var mViewModel: FetcherViewModel

    @Before
    fun setUp() {
        dataRepositry = DataRepositry(mService = mService)
        mViewModel = FetcherViewModel(dataRepositry)
    }

    @Test
    fun `test_api`() {
        // Mock Response
        mViewModel.getServerPost()
        val captor = ArgumentCaptor.forClass(FetcherModelItem::class.java)
        captor.run {
            verify(mockObserver, times(1))
            assertNotNull(value)
        }
        mViewModel.getPostLiveData().observeForever(mockObserver)
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
}
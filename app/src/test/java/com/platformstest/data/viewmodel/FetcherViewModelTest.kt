package com.platformstest.data.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.platformstest.data.repositry.DataRepositry
import com.platformstest.data.services.RestDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@ExperimentalCoroutinesApi
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

    private lateinit var mViewModel: FetcherViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        Dispatchers.setMain(mainThreadSurrogate)
        dataRepositry = DataRepositry(mService = mService)
        mViewModel = FetcherViewModel(dataRepositry)
    }

    @Test
    fun `test_api_fail`() = runBlockingTest {
        // Mock Response
        val userResponseData = dataRepositry.getData()
        Assert.assertNull(userResponseData)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}
package com.platformstest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.platformstest.data.viewmodel.FetcherViewModel
import com.platformstest.di.annotations.ApplicationScope
import com.platformstest.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides map of all ViewModels and a ViewModelFactory for dependencies
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FetcherViewModel::class)
    abstract fun bindFetchModel(mainViewModel: FetcherViewModel): ViewModel

    @Binds
    @ApplicationScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
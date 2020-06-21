package com.base.zuinkfleet.di.modules

import com.platformstest.data.services.RestDataService
import com.platformstest.di.annotations.ApplicationScope
import com.platformstest.di.modules.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Provides network services for data
 */
@Module(includes = [NetworkModule::class])
class NetworkServiceModule {

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): RestDataService {
        return retrofit.create(RestDataService::class.java)
    }
}
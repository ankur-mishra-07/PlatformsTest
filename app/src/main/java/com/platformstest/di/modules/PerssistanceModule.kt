package com.platformstest.di.modules

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.platformstest.data.services.LocalDataServiceDao
import com.platformstest.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PerssistanceModule {
    @Provides
    @ApplicationScope
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "peopledata.db")
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideMovieDao(@NonNull database: AppDatabase): LocalDataServiceDao {
        return database.peopleDao()
    }
}
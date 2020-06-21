package com.platformstest.di.modules

import androidx.room.Database
import androidx.room.RoomDatabase
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.data.services.LocalDataServiceDao

@Database(entities = [(FetcherModelItem::class)], version = 1, exportSchema = false)
abstract class Datamodules : RoomDatabase() {
    abstract fun peopleDao(): LocalDataServiceDao
}
package com.platformstest.data.services

import androidx.room.*
import com.platformstest.data.models.FetcherModelItem

@Dao
interface LocalDataServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeopleList(peopleList: List<FetcherModelItem>)

    @Update
    fun updatePeople(people: FetcherModelItem)

    @Query("SELECT * FROM people_table")
    fun getPeopleList(): List<FetcherModelItem>
}
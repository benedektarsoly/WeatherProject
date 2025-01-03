package com.techmania.weatherproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techmania.weatherproject.data.database.table.WeatherInfoCurrent

@Dao
interface WeatherInfoCurrentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg infos: WeatherInfoCurrent)

    @Query("DELETE FROM weatherinfocurrent")
    fun nukeAll()

    @Query("SELECT * FROM weatherinfocurrent")
    fun getAll(): WeatherInfoCurrent?
}
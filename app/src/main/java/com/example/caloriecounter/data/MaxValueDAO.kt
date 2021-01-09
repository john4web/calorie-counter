package com.example.caloriecounter.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MaxValueDAO {
//Simple MaxValueDAO for CRUD-Operations

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMaxValue(maxValue: MaxValue)

    @Query("SELECT * FROM max_values_table ORDER BY id ASC")
    fun getAllMaxValues(): LiveData<List<MaxValue>>

}
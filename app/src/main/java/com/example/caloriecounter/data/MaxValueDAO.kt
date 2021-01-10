package com.example.caloriecounter.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MaxValueDAO {

    @Query("SELECT value FROM max_values_table WHERE type = 'MAXCALORIES'")
    fun getCalorieMaxValue(): LiveData<Int>

    @Query("SELECT value FROM max_values_table WHERE type = 'MAXLITRES'")
    fun getLitreMaxValue(): LiveData<Int>

    @Query("UPDATE max_values_table SET value = :newValue WHERE type = :type")
    fun updateMaxValue(newValue: Int, type: String)

}
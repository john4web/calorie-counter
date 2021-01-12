package com.example.caloriecounter.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeverageDAO {

    @Query("SELECT * FROM beverages_table ORDER BY id ASC")
    fun getAllBeverages() : LiveData<List<Beverage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBeverage(beverage: Beverage)

    @Delete
    fun deleteBeverage(beverage: Beverage)

}
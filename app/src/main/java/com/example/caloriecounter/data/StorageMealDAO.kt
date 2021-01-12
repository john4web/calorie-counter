package com.example.caloriecounter.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StorageMealDAO {

    @Query("SELECT * FROM storage_meals_table ORDER BY id ASC")
    fun getAllStorageMeals() : LiveData<List<StorageMeal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStorageMeal(storageMeal: StorageMeal)

    @Delete
    fun deleteStorageMeal(storageMeal: StorageMeal)

}
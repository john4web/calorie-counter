package com.example.caloriecounter.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MealDAO {

    @Query("SELECT * FROM meals_table ORDER BY id ASC")
    fun getAllMeals() : LiveData<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeal(meal: Meal)

    @Delete
    fun deleteMeal(meal: Meal)

}
package com.example.caloriecounter.data

import androidx.lifecycle.LiveData

class MealRepository(private val mealDAO: MealDAO) {

    val getAllMeals: LiveData<List<Meal>> = mealDAO.getAllMeals()

    suspend fun addMeal(meal: Meal){
        mealDAO.addMeal(meal)
    }

    suspend fun deleteMeal(meal: Meal){
        mealDAO.deleteMeal(meal)
    }

}
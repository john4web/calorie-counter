package com.example.caloriecounter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MealRepository

    val getAllMeals: LiveData<List<Meal>>

   //This gets always executed when MaxValueViewModel is called. The init block will execute immediately after the primary constructor.
init{
    val mealDAO = AppDatabase.getDatabase(application).mealDao()
       repository = MealRepository(mealDAO)
       getAllMeals = repository.getAllMeals
}

    fun addMeal(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeal(meal)
        }
    }

}
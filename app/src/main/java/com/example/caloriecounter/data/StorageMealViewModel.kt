package com.example.caloriecounter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StorageMealViewModel(application: Application): AndroidViewModel(application) {

    private val repository: StorageMealRepository

    val getAllStorageMeals: LiveData<List<StorageMeal>>

   //This gets always executed when MealViewModel is called. The init block will execute immediately after the primary constructor.
init{
    val storageMealDAO = AppDatabase.getDatabase(application).storageMealDao()
       repository = StorageMealRepository(storageMealDAO)
       getAllStorageMeals = repository.getAllStorageMeals
}

    fun addStorageMeal(storageMeal: StorageMeal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStorageMeal(storageMeal)
        }
    }

    fun deleteStorageMeal(storageMeal: StorageMeal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStorageMeal(storageMeal)
        }
    }

}
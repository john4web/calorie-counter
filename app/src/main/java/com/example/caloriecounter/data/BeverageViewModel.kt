package com.example.caloriecounter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeverageViewModel(application: Application): AndroidViewModel(application) {

    private val repository: BeverageRepository

    val getAllBeverages: LiveData<List<Beverage>>

   //This gets always executed when BeverageViewModel is called. The init block will execute immediately after the primary constructor.
init{
    val beverageDAO = AppDatabase.getDatabase(application).beverageDao()
       repository = BeverageRepository(beverageDAO)
       getAllBeverages = repository.getAllBeverages
}

    fun addBeverage(beverage: Beverage){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBeverage(beverage)
        }
    }

    fun deleteBeverage(beverage: Beverage){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBeverage(beverage)
        }
    }

}
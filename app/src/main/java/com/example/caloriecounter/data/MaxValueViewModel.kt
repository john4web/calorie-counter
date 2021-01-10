package com.example.caloriecounter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaxValueViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MaxValueRepository

    val getCalorieMaxValue: LiveData<Int>
    val getLitreMaxValue: LiveData<Int>


   //This gets always executed when MaxValueViewModel is called. The init block will execute immediately after the primary constructor.
init{
    val maxValueDAO = AppDatabase.getDatabase(application).maxValueDao()
       repository = MaxValueRepository(maxValueDAO)
       getCalorieMaxValue = repository.getCalorieMaxValue
       getLitreMaxValue = repository.getLitreMaxValue
}

    fun updateMaxValue(maxValue: MaxValue){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMaxValue(maxValue)
        }
    }

}
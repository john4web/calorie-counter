package com.example.caloriecounter.data

import androidx.lifecycle.LiveData

class MaxValueRepository(private val maxValueDAO: MaxValueDAO) {

    val getCalorieMaxValue: LiveData<Int> = maxValueDAO.getCalorieMaxValue()
    val getLitreMaxValue: LiveData<Int> = maxValueDAO.getLitreMaxValue()

    suspend fun updateMaxValue(maxValue: MaxValue){
        maxValueDAO.updateMaxValue(maxValue.value, maxValue.type)
    }

}
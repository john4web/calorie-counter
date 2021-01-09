package com.example.caloriecounter.data

import androidx.lifecycle.LiveData

class MaxValueRepository(private val maxValueDAO: MaxValueDAO) {

    val getAllMaxValues: LiveData<List<MaxValue>> = maxValueDAO.getAllMaxValues()
    suspend fun addMaxValue(maxValue: MaxValue){
        maxValueDAO.addMaxValue(maxValue)
    }

}
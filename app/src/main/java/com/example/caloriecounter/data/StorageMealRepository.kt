package com.example.caloriecounter.data

import androidx.lifecycle.LiveData

class StorageMealRepository(private val storageMealDAO: StorageMealDAO) {

    val getAllStorageMeals: LiveData<List<StorageMeal>> = storageMealDAO.getAllStorageMeals()

    suspend fun addStorageMeal(storageMeal: StorageMeal){
        storageMealDAO.addStorageMeal(storageMeal)
    }

    suspend fun deleteStorageMeal(storageMeal: StorageMeal){
        storageMealDAO.deleteStorageMeal(storageMeal)
    }

}
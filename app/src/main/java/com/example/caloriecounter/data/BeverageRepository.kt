package com.example.caloriecounter.data

import androidx.lifecycle.LiveData

class BeverageRepository(private val beverageDAO: BeverageDAO) {

    val getAllBeverages: LiveData<List<Beverage>> = beverageDAO.getAllBeverages()

    suspend fun addBeverage(beverage: Beverage){
        beverageDAO.addBeverage(beverage)
    }

    suspend fun deleteBeverage(beverage: Beverage){
        beverageDAO.deleteBeverage(beverage)
    }

}
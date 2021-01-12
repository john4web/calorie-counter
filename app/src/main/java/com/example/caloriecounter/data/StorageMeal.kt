package com.example.caloriecounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage_meals_table")
data class StorageMeal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Int
)
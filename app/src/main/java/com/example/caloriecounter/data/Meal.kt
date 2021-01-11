package com.example.caloriecounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals_table")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Int
)
package com.example.caloriecounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beverages_table")
data class Beverage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val litres: Float
)
package com.example.caloriecounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "max_values_table")
data class MaxValue(
    @PrimaryKey val id: Int,
    val type: String,
    val value: Int
)
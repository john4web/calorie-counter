package com.example.caloriecounter

interface ValueDAO {

    fun saveValue(value: Value)

    fun readValue(id: String): Value

    fun readAllValues(): List<Value>

    fun updateValue(value: Value)

    fun removeValue(value: Value)

}
package com.example.caloriecounter.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MaxValue::class, Meal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun maxValueDao(): MaxValueDAO
    abstract fun mealDao(): MealDAO


    //This is the singleton class
    // companion object in Kotlin is a static approach. everything in that block are static members of the class AppDatabase
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).createFromAsset("database/prepopulate.db").build()

                INSTANCE = instance
                if(INSTANCE == null){
                    Log.d("ABCDEFG", "NULL!")
                }else{
                    Log.d("ABCDEFG", "NOT NULL!")
                }
                return instance
            }


        }
    }
}
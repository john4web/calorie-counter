package com.example.caloriecounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MaxValue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun maxValueDao(): MaxValueDAO


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
                ).createFromAsset("database/myapp.db").build()
                INSTANCE = instance
                return instance
            }


        }
    }
}
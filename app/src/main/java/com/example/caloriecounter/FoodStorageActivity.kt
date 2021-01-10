package com.example.caloriecounter

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_food_storage.*

class FoodStorageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_storage)



        fragment_food_storage_button_add.setOnClickListener {

        }

        fragment_food_storage_button_close.setOnClickListener {
            finish()
        }


    }
}
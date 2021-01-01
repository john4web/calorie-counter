package com.example.caloriecounter

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_eat.*
import kotlinx.android.synthetic.main.activity_settings.*

class EatActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat)



        activity_eat_button_eatNow.setOnClickListener {


            finish()

        }

        activity_eat_button_cancel.setOnClickListener {


            finish()

        }


    }
}
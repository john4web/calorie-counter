package com.example.caloriecounter

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_eat.*

class EatActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eat)



        fragment_eat_button_eatNow.setOnClickListener {


            finish()

        }

        fragment_eat_button_cancel.setOnClickListener {


            finish()

        }


    }
}
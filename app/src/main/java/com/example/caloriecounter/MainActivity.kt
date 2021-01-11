package com.example.caloriecounter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.caloriecounter.fragments.MainFragment

const val TAG: String = "Main-Activity"

class MainActivity : AppCompatActivity(), ICommunicator{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()

        // only setting the fragment to mainFragment when activity is started for the first time
        if(savedInstanceState == null) {
            //setting the initial Fragment
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_frameLayout, mainFragment)
                commit()
            }
        }
/*
        activity_main_progressBar_calories.max=1000
        activity_main_progressBar_litres.max=3

        val currentProgressCalories = 500
        val currentProgressLitres = 2

        ObjectAnimator.ofInt(activity_main_progressBar_calories, "progress", currentProgressCalories).setDuration(2000).start()
        ObjectAnimator.ofInt(activity_main_progressBar_litres, "progress", currentProgressLitres).setDuration(0).start()
*/

    }

    override fun switchToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.activity_main_frameLayout, fragment)
            commit()
        }
    }

}
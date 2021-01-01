package com.example.caloriecounter

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val TAG: String = "Main-Activity"

const val SHARED_PREF_FILE_ID_LIMITS = "LIMITS"
const val SHARED_PREF_FILE_ID_EATSTORAGE_NAME = "EATSTORAGE-NAME"
const val SHARED_PREF_FILE_ID_EATSTORAGE_CALORIES = "EATSTORAGE-CALORIES"
const val SHARED_PREF_VALUE_KEY_MAXCAL = "MaxCalories"
const val SHARED_PREF_VALUE_KEY_MAXLIT = "MaxLitres"

class MainActivity : Activity(), View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        activity_main_progressBar_calories.max=1000
        activity_main_progressBar_litres.max=3

        val currentProgressCalories = 500
        val currentProgressLitres = 2

        ObjectAnimator.ofInt(activity_main_progressBar_calories, "progress", currentProgressCalories).setDuration(2000).start()
        ObjectAnimator.ofInt(activity_main_progressBar_litres, "progress", currentProgressLitres).setDuration(0).start()

        //Setting OnClick-Listeners
        activity_main_button_drink.setOnClickListener(this)
        activity_main_button_eat.setOnClickListener(this)
        activity_main_button_settings.setOnClickListener(this)
        activity_main_button_storedFood.setOnClickListener(this)


        val sp: SharedPreferences = getSharedPreferences(SHARED_PREF_FILE_ID_LIMITS, MODE_PRIVATE)
        sp.registerOnSharedPreferenceChangeListener(this)




        //get current value from
        val storedMaxCalories: Int = sp.getInt(SHARED_PREF_VALUE_KEY_MAXCAL, 1000)
        val storedMaxLitres: Int = sp.getInt(SHARED_PREF_VALUE_KEY_MAXLIT, 3)
        activity_main_textview_maxCalories.text = storedMaxCalories.toString()
        activity_main_textview_maxLitres.text = storedMaxLitres.toString()




    }


    override fun onClick(_v: View?) {


        var i: Intent? = null

        when(_v?.id) {

            R.id. activity_main_button_drink -> {
                Log.d(TAG, "MainActivity::onClick -> Button drink has been clicked")
            }
            R.id. activity_main_button_eat -> {
                Log.d(TAG, "MainActivity::onClick -> Button eat has been clicked")
                i = Intent(this, EatActivity::class.java)
            }
            R.id. activity_main_button_settings -> {
                Log.d(TAG, "MainActivity::onClick -> Button settings has been clicked")
                i = Intent(this, SettingsActivity::class.java)

            }
            R.id. activity_main_button_storedFood -> {
                Log.d(TAG, "MainActivity::onClick -> Button storedFood has been clicked")
                i = Intent(this, FoodStorageActivity::class.java)
            }
            else ->{
                Log.d(TAG, "MainActivity::onClick -> _view null or unhandled id encountered:")
            }
        }

        i?.let{
            startActivity(i)
        } ?: Log.e(TAG,"MainActivity::onClick -> Intent was not set")

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == SHARED_PREF_VALUE_KEY_MAXCAL){   //if Max-Calorie-Value has changed in the XML-File
             val storedMaxCalories: Int? = sharedPreferences?.getInt(SHARED_PREF_VALUE_KEY_MAXCAL, 1000)
            //set changed value to textview in mainactivity
            activity_main_textview_maxCalories.text = storedMaxCalories.toString()
        }

        if(key == SHARED_PREF_VALUE_KEY_MAXLIT){ //if Max-Litres-Value has changed in the XML-File
            val storedMaxLitres: Int? = sharedPreferences?.getInt(SHARED_PREF_VALUE_KEY_MAXLIT, 3)
            //set changed value to textview in mainactivity
            activity_main_textview_maxLitres.text = storedMaxLitres.toString()
        }
    }


}
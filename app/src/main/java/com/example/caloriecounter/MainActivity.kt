package com.example.caloriecounter

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*

const val TAG: String = "Main-Activity"

const val SHARED_PREF_FILE_ID_VALUES = "VALUES"
const val SHARED_PREF_VALUE_KEY_MAXCAL = "MaxCalories"
const val SHARED_PREF_VALUE_KEY_MAXLIT = "MaxLitres"

class MainActivity : Activity(), View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    val spdao: ValueDAO = SharedPreferencesValueDAOImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Setting OnClick-Listeners
        activity_main_button_drink.setOnClickListener(this)
        activity_main_button_eat.setOnClickListener(this)
        activity_main_button_settings.setOnClickListener(this)
        activity_main_button_storedFood.setOnClickListener(this)

        val sp: SharedPreferences = getSharedPreferences(SHARED_PREF_FILE_ID_VALUES, MODE_PRIVATE)
        sp.registerOnSharedPreferenceChangeListener(this)

        //get current value from db
        val val1: Value = spdao.readValue(SHARED_PREF_VALUE_KEY_MAXCAL)
        val val2: Value = spdao.readValue(SHARED_PREF_VALUE_KEY_MAXLIT)
        activity_main_textview_maxCalories.text = val1.value
        activity_main_textview_maxLitres.text = val2.value

    }


    override fun onClick(_v: View?) {


        var i: Intent? = null

        when(_v?.id) {

            R.id. activity_main_button_drink -> {
                Log.d(TAG, "MainActivity::onClick -> Button drink has been clicked")
            }
            R.id. activity_main_button_eat -> {
                Log.d(TAG, "MainActivity::onClick -> Button eat has been clicked")
            }
            R.id. activity_main_button_settings -> {
                Log.d(TAG, "MainActivity::onClick -> Button settings has been clicked")


                i = Intent(this, SettingsActivity::class.java)

            }
            R.id. activity_main_button_storedFood -> {
                Log.d(TAG, "MainActivity::onClick -> Button storedFood has been clicked")
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
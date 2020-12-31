package com.example.caloriecounter

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val spdao: ValueDAO = SharedPreferencesValueDAOImpl(this)
        Log.d("ABC", "Settings Created")


        //get current value from db
        val value1: Value = spdao.readValue(SHARED_PREF_VALUE_KEY_MAXCAL)
        val value2: Value = spdao.readValue(SHARED_PREF_VALUE_KEY_MAXLIT)
         activity_settings_input_kcal.setText(value1.value)
         activity_settings_input_litres.setText(value2.value)




        //Setting OnClick-Listeners
        activity_settings_button_save.setOnClickListener {

            val kcalInputNumber: String = activity_settings_input_kcal.text.toString()
            val litresInputNumber: String = activity_settings_input_litres.text.toString()

            val value1: Value = Value()
            value1.key = SHARED_PREF_VALUE_KEY_MAXCAL
            value1.value = kcalInputNumber


            val value2: Value = Value()
            value2.key = SHARED_PREF_VALUE_KEY_MAXLIT
            value2.value = litresInputNumber

            spdao.saveValue(value1)
            spdao.saveValue(value2)

            //Close the Activity
            finish()
        }

    }

}
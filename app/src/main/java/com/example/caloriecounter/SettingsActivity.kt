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

        Log.d("ABC", "Settings Created")


        //get current value from
            val xsp: SharedPreferences = getSharedPreferences(SHARED_PREF_FILE_ID, MODE_PRIVATE)
            val storedMaxCalories: Int = xsp.getInt(SHARED_PREF_VALUE_KEY_MAXCAL, 1000)
          val storedMaxLitres: Int = xsp.getInt(SHARED_PREF_VALUE_KEY_MAXLIT, 3)
         activity_settings_input_kcal.setText(storedMaxCalories.toString())
         activity_settings_input_litres.setText(storedMaxLitres.toString())




        //Setting OnClick-Listeners
        activity_settings_button_save.setOnClickListener {

            val kcalInputNumber: Int = activity_settings_input_kcal.text.toString().toInt()
            val litresInputNumber: Int = activity_settings_input_litres.text.toString().toInt()

            val sp: SharedPreferences = getSharedPreferences(SHARED_PREF_FILE_ID, MODE_PRIVATE)
            val edt: SharedPreferences.Editor = sp.edit()
            edt.putInt(SHARED_PREF_VALUE_KEY_MAXCAL, kcalInputNumber)
            edt.putInt(SHARED_PREF_VALUE_KEY_MAXLIT, litresInputNumber)

            //Write the typed in number into the XML-File stored on the Smartphone-Harddrive
            edt.commit()

            //Close the Activity
            finish()
        }





    }




    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("ABC", "Settings SAVED")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.d("ABC", "Settings RESTORED")

    }





}
package com.example.caloriecounter

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.caloriecounter.data.MaxValue
import com.example.caloriecounter.data.MaxValueViewModel
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : Activity() {

    private lateinit var myMaxValueViewModel: MaxValueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        Log.d("ABC", "Settings Created")

/*
        //get current value from
            val xsp: SharedPreferences = getSharedPreferences(SHARED_PREF_FILE_ID_LIMITS, MODE_PRIVATE)
            val storedMaxCalories: Int = xsp.getInt(SHARED_PREF_VALUE_KEY_MAXCAL, 1000)
          val storedMaxLitres: Int = xsp.getInt(SHARED_PREF_VALUE_KEY_MAXLIT, 3)
         activity_settings_input_kcal.setText(storedMaxCalories.toString())
         activity_settings_input_litres.setText(storedMaxLitres.toString())

*/


        //Setting OnClick-Listeners
        fragment_settings_button_save.setOnClickListener {
/*
            val kcalInputNumber: Int = activity_settings_input_kcal.text.toString().toInt()
            val litresInputNumber: Int = activity_settings_input_litres.text.toString().toInt()

            val sp: SharedPreferences = getSharedPreferences(SHARED_PREF_FILE_ID_LIMITS, MODE_PRIVATE)
            val edt: SharedPreferences.Editor = sp.edit()
            edt.putInt(SHARED_PREF_VALUE_KEY_MAXCAL, kcalInputNumber)
            edt.putInt(SHARED_PREF_VALUE_KEY_MAXLIT, litresInputNumber)

            //Write the typed in number into the XML-File stored on the Smartphone-Harddrive
            edt.commit()
*/

            insertDataIntoDatabase()

            //Close the Activity
            finish()
        }


    //myMaxValueViewModel = ViewModelProvider(this).get(MaxValueViewModel::class.java)


    }


    private fun insertDataIntoDatabase(){
        val maxcal = fragment_settings_input_kcal.text.toString()
        val maxlit = fragment_settings_input_litres.text.toString()

        if(inputCheck(maxcal, maxlit)){

            val maxValue1 = MaxValue(0, "CALORIES", maxcal.toInt())
            val maxValue2 = MaxValue(1, "LITRES", maxcal.toInt())

        }else{


        }



    }

    private fun inputCheck(maxcal: String, maxlit: String): Boolean{

        //TODO: implement inputcheck
        return true
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
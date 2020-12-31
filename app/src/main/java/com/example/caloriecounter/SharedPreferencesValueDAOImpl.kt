package com.example.caloriecounter

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import kotlinx.android.synthetic.main.activity_settings.*

class SharedPreferencesValueDAOImpl(private val context: Context) : ValueDAO {

    override fun saveValue(value: Value) {

        //übergeben: key, value, fileID

        val sp: SharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_ID_VALUES, Activity.MODE_PRIVATE)
        val edt: SharedPreferences.Editor = sp.edit()

        edt.putInt(value.key, value.value.toInt())

        //Write the typed in number into the XML-File stored on the Smartphone-Harddrive
         edt.commit()
    }

    override fun readValue(id: String): Value {

        val sp: SharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_ID_VALUES, Activity.MODE_PRIVATE)

        val valueFromDB: Int = sp.getInt(id, 1000)
        val value: Value = Value()
        value.key = id
        value.value = valueFromDB.toString()
        return value

    }

    override fun readAllValues(): List<Value> {
        TODO("Not yet implemented")
    }

    override fun updateValue(value: Value) {
        TODO("Not yet implemented")
    }

    override fun removeValue(value: Value) {
        TODO("Not yet implemented")
    }
}
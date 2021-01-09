package com.example.caloriecounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.caloriecounter.data.MaxValue
import com.example.caloriecounter.data.MaxValueViewModel
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.activity_settings_button_save
import kotlinx.android.synthetic.main.fragment_settings.activity_settings_input_kcal
import kotlinx.android.synthetic.main.fragment_settings.activity_settings_input_litres


class SettingsFragment : Fragment() {

    private lateinit var communicator: ICommunicator
    private lateinit var myMaxValueViewModel: MaxValueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        myMaxValueViewModel = ViewModelProvider(this).get(MaxValueViewModel::class.java)

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        activity_settings_button_save.setOnClickListener{

            insertDataIntoDatabase()

            communicator.switchToFragment(MainFragment())
        }

    }




    private fun insertDataIntoDatabase(){
        val maxcal = activity_settings_input_kcal.text.toString()
        val maxlit = activity_settings_input_litres.text.toString()

        if(inputCheck(maxcal, maxlit)){

            val maxValue1 = MaxValue(0, "CALORIES", maxcal.toInt())
            val maxValue2 = MaxValue(1, "LITRES", maxcal.toInt())

            myMaxValueViewModel.addMaxValue(maxValue1)
            myMaxValueViewModel.addMaxValue(maxValue2)
            Toast.makeText(requireContext(), "Erfolgreich gespeichert", Toast.LENGTH_LONG).show()

        }else{

            Toast.makeText(requireContext(), "Fehler!", Toast.LENGTH_LONG).show()
        }



    }

    private fun inputCheck(maxcal: String, maxlit: String): Boolean{

        //TODO: implement inputcheck
        return true
    }



}
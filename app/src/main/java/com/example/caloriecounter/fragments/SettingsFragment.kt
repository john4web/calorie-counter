package com.example.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.R
import com.example.caloriecounter.data.MaxValue
import com.example.caloriecounter.data.MaxValueViewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_settings.*


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

        //When pressing save, the data is stored in the database
        fragment_settings_button_save.setOnClickListener{
            insertDataIntoDatabase()

        }

        //When pressing cancel, the main fragment is shown
        fragment_settings_button_cancel.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

        //Prefill the two input fields with the maxvalues from the database
        myMaxValueViewModel.getCalorieMaxValue.observe(viewLifecycleOwner, Observer { value ->
            fragment_settings_input_kcal.setText(value.toString())
        })

        myMaxValueViewModel.getLitreMaxValue.observe(viewLifecycleOwner, Observer { value ->
            fragment_settings_input_litres.setText(value.toString())
        })


    }



    //This method inserts the maxValue of Calories and Litres in the database
    private fun insertDataIntoDatabase(){
        val maxcal = fragment_settings_input_kcal.text.toString()
        val maxlit = fragment_settings_input_litres.text.toString()

        if(inputCheck(maxcal, maxlit)){

            val maxValue1 = MaxValue(0, "MAXCALORIES", maxcal.toInt())
            val maxValue2 = MaxValue(0, "MAXLITRES", maxlit.toInt())

            myMaxValueViewModel.updateMaxValue(maxValue1)
            myMaxValueViewModel.updateMaxValue(maxValue2)

            Toast.makeText(requireContext(), "Erfolgreich gespeichert", Toast.LENGTH_LONG).show()
            communicator.switchToFragment(MainFragment())

        }else{

            Toast.makeText(requireContext(), "Falsche Eingabe!", Toast.LENGTH_LONG).show()
        }

    }

    //This method validates the user input
    private fun inputCheck(maxcal: String, maxlit: String): Boolean{

        try {
            maxcal.toInt()
            maxlit.toInt()
        }catch (e: Exception){
            return false
        }

        return true
    }



}
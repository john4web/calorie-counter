package com.example.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.R
import com.example.caloriecounter.data.MaxValueViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var communicator: ICommunicator
    private lateinit var myMaxValueViewModel: MaxValueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        fragment_main_button_settings.setOnClickListener{
            communicator.switchToFragment(SettingsFragment())
        }

        fragment_main_button_eat.setOnClickListener{
            communicator.switchToFragment(EatFragment())
        }

        fragment_main_button_storedFood.setOnClickListener{
            communicator.switchToFragment(FoodStorageFragment())
        }

        fragment_main_button_drink.setOnClickListener{

        }


        myMaxValueViewModel = ViewModelProvider(this).get(MaxValueViewModel::class.java)
        /*myMaxValueViewModel.getAllMaxValues.observe(this, Observer{maxValue ->

        })*/

        myMaxValueViewModel.getCalorieMaxValue.observe(viewLifecycleOwner, Observer { value ->
            fragment_main_textview_maxCalories.text = value.toString()
        })


        myMaxValueViewModel.getLitreMaxValue.observe(viewLifecycleOwner, Observer { value ->
            fragment_main_textview_maxLitres.text = value.toString()
        })


    }

}
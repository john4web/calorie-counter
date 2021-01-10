package com.example.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.R
import kotlinx.android.synthetic.main.fragment_food_storage.*

class FoodStorageFragment : Fragment() {

    private lateinit var communicator: ICommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_storage, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        fragment_food_storage_button_add.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

        fragment_food_storage_button_close.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

    }



}
package com.example.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.databinding.FragmentFoodStorageBinding

class FoodStorageFragment : Fragment() {

    private var _binding: FragmentFoodStorageBinding? = null
    private val binding get() = _binding!!

    private lateinit var communicator: ICommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_food_storage, container, false)

        _binding = FragmentFoodStorageBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        binding.fragmentFoodStorageButtonAdd.setOnClickListener{


        }

        binding.fragmentFoodStorageButtonClose.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
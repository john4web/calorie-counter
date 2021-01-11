package com.example.caloriecounter.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.data.MaxValueViewModel
import com.example.caloriecounter.data.MealViewModel
import com.example.caloriecounter.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var communicator: ICommunicator
    private lateinit var myMaxValueViewModel: MaxValueViewModel
    private lateinit var myMealViewModel: MealViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)




        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        //Recyclerview
        val adapter = MealListAdapter {
            meal -> myMealViewModel.deleteMeal(meal)
        }
        val recyclerView = binding.fragmentMainRecyclerViewMeals
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Viewmodel
        myMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        myMealViewModel.getAllMeals.observe(viewLifecycleOwner, Observer { meal ->
            adapter.setData(meal)
        })


        binding.fragmentMainButtonSettings.setOnClickListener{
            communicator.switchToFragment(SettingsFragment())
        }

        binding.fragmentMainButtonEat.setOnClickListener{
            communicator.switchToFragment(EatFragment())
        }

        binding.fragmentMainButtonStoredFood.setOnClickListener{
            communicator.switchToFragment(FoodStorageFragment())
        }

        binding.fragmentMainButtonDrink.setOnClickListener{

        }


        myMaxValueViewModel = ViewModelProvider(this).get(MaxValueViewModel::class.java)


        myMaxValueViewModel.getCalorieMaxValue.observe(viewLifecycleOwner, Observer { value ->
            binding.fragmentMainTextviewMaxCalories.text = value.toString()
        })


        myMaxValueViewModel.getLitreMaxValue.observe(viewLifecycleOwner, Observer { value ->
            binding.fragmentMainTextviewMaxLitres.text = value.toString()
        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }






}
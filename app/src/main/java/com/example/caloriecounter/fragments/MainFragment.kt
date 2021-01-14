package com.example.caloriecounter.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.data.*
import com.example.caloriecounter.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var communicator: ICommunicator
    private lateinit var myMaxValueViewModel: MaxValueViewModel
    private lateinit var myMealViewModel: MealViewModel
    private lateinit var myBeverageViewModel: BeverageViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        //Recyclerview mealList
        val mealAdapter = MealListAdapter {
            meal -> myMealViewModel.deleteMeal(meal)
        }
        val mealRecyclerView = binding.fragmentMainRecyclerViewMealList
        mealRecyclerView.adapter = mealAdapter
        mealRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Viewmodel
        myMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        myMealViewModel.getAllMeals.observe(viewLifecycleOwner, Observer { meal ->

            var calorieCounter:Int = 0
            meal.forEach {
                calorieCounter += it.calories
            }

            mealAdapter.setData(meal)
            ObjectAnimator.ofInt(binding.fragmentMainProgressBarCalories, "progress", calorieCounter).setDuration(2000).start()
        })



        //Recyclerview beveragelList
        val beverageAdapter = BeverageListAdapter {
            beverage -> myBeverageViewModel.deleteBeverage(beverage)
        }
        val beverageRecyclerView = binding.fragmentMainRecyclerViewBeverageList
        beverageRecyclerView.adapter = beverageAdapter
        beverageRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Viewmodel
        myBeverageViewModel = ViewModelProvider(this).get(BeverageViewModel::class.java)
        myBeverageViewModel.getAllBeverages.observe(viewLifecycleOwner, Observer { beverage ->

            var litreCounter:Int = 0

            beverage.forEach {
                litreCounter += it.litres
            }

            binding.fragmentMainProgressBarLitres.progress = litreCounter

            beverageAdapter.setData(beverage)

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
            val newBeverage = Beverage(0, "Glas Wasser", 1)
            myBeverageViewModel.addBeverage(newBeverage)
            Toast.makeText(requireContext(), "1 Liter getrunken", Toast.LENGTH_LONG).show()
        }


        myMaxValueViewModel = ViewModelProvider(this).get(MaxValueViewModel::class.java)


        myMaxValueViewModel.getCalorieMaxValue.observe(viewLifecycleOwner, Observer { value ->
            binding.fragmentMainTextviewMaxCalories.text = value.toString()
            binding.fragmentMainProgressBarCalories.max = value
        })


        myMaxValueViewModel.getLitreMaxValue.observe(viewLifecycleOwner, Observer { value ->
            binding.fragmentMainTextviewMaxLitres.text = value.toString()
            binding.fragmentMainProgressBarLitres.max = value
        })

        /*
        activity_main_progressBar_calories.max=1000
        activity_main_progressBar_litres.max=3

        val currentProgressCalories = 500
        val currentProgressLitres = 2

        ObjectAnimator.ofInt(activity_main_progressBar_calories, "progress", currentProgressCalories).setDuration(2000).start()
        ObjectAnimator.ofInt(activity_main_progressBar_litres, "progress", currentProgressLitres).setDuration(0).start()
*/






    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateProgressBars(){


    }


}
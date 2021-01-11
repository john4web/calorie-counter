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
import com.example.caloriecounter.data.Meal
import com.example.caloriecounter.databinding.FragmentEatBinding
import com.example.caloriecounter.data.MealViewModel

class EatFragment : Fragment() {

    private var _binding: FragmentEatBinding? = null
    private val binding get() = _binding!!
    private lateinit var communicator: ICommunicator
    private lateinit var myMealViewModel: MealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        myMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        // Inflate the layout for this fragment
        _binding = FragmentEatBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_eat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        binding.fragmentEatButtonEatNow.setOnClickListener{

            insertMealIntoDatabase()


        }

        binding.fragmentEatButtonCancel.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    //This method inserts the typed in meal in the database
    private fun insertMealIntoDatabase(){
        val mealName = binding.fragmentEatInputName.text.toString()
        val mealCalories = binding.fragmentEatInputCalories.text.toString()

        if(inputCheck(mealCalories)){

            val newMeal = Meal(0, mealName, mealCalories.toInt())
            myMealViewModel.addMeal(newMeal)

            Toast.makeText(requireContext(), "Mahlzeit erfolgreich gespeichert", Toast.LENGTH_LONG).show()
            communicator.switchToFragment(MainFragment())

        }else{

            Toast.makeText(requireContext(), "Falsche Eingabe!", Toast.LENGTH_LONG).show()
        }

    }

    //This method validates the user input
    private fun inputCheck(mealCalories: String): Boolean{

        try {
            mealCalories.toInt()
        }catch (e: Exception){
            return false
        }

        return true
    }








}
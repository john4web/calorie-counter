package com.example.caloriecounter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.data.Meal
import com.example.caloriecounter.data.MealViewModel
import com.example.caloriecounter.data.StorageMeal
import com.example.caloriecounter.data.StorageMealViewModel
import com.example.caloriecounter.databinding.FragmentEatBinding


class EatFragment : Fragment() {

    private var _binding: FragmentEatBinding? = null
    private val binding get() = _binding!!
    private lateinit var communicator: ICommunicator
    private lateinit var myMealViewModel: MealViewModel
    private lateinit var myStorageMealViewModel: StorageMealViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        myMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        myStorageMealViewModel = ViewModelProvider(this).get(StorageMealViewModel::class.java)


        // Inflate the layout for this fragment
        _binding = FragmentEatBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_eat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        binding.fragmentEatButtonEatNow.setOnClickListener {
            insertMealIntoDatabase()
        }

        binding.fragmentEatButtonCancel.setOnClickListener {
            communicator.switchToFragment(MainFragment())
        }

        val mySpinner = binding.fragmentEatSpinner
        var spinnerStringArray = arrayListOf<String>()
        var spinnerStorageMealArray = arrayListOf<StorageMeal>()
        var selectedItem: StorageMeal? = null

        //This is executed everytime the storageMeals change in the database
        myStorageMealViewModel.getAllStorageMeals.observe(viewLifecycleOwner, Observer { storageMeal ->

            //Clear arrays completely
            spinnerStringArray.clear()
            spinnerStorageMealArray.clear()

            //And fill it again with the updated values
            storageMeal.forEach{
                spinnerStringArray.add("${it.name}: ${it.calories}kcal")
                spinnerStorageMealArray.add(it)
            }

            var adapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, spinnerStringArray)
            mySpinner.adapter=adapter

        })


        mySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            //Gets executed when user selects item
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = spinnerStorageMealArray[position]
                Toast.makeText(requireContext(), selectedItem.toString(), Toast.LENGTH_LONG).show()
            }

            //Gets executed when nothing is selected
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Nothing is selected", Toast.LENGTH_LONG).show()
            }

        }


        binding.fragmentEatButtonEatNowStorageMeal.setOnClickListener {
            insertStorageMealIntoDatabase(selectedItem)
        }



        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    //Here, the chosen storageMeal gets inserted into the database as Meal
    private fun insertStorageMealIntoDatabase(selectedItem: StorageMeal?){

        //check, if user has chosen an item
        if(selectedItem != null) {
            //convert StorageMeal into Meal and insert it into database
            val newMeal = Meal(0, selectedItem.name, selectedItem.calories)
            myMealViewModel.addMeal(newMeal)
            communicator.switchToFragment(MainFragment())
            Toast.makeText(requireContext(), "${selectedItem.name} gegessen!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "Es sind noch keine Lebensmittel im Speicher vorhanden", Toast.LENGTH_LONG).show()
        }



    }



        //This method inserts the typed in meal in the database
        private fun insertMealIntoDatabase() {
            val mealName = binding.fragmentEatInputName.text.toString()
            val mealCalories = binding.fragmentEatInputCalories.text.toString()

            if (inputCheck(mealCalories)) {

                val newMeal = Meal(0, mealName, mealCalories.toInt())
                myMealViewModel.addMeal(newMeal)

                Toast.makeText(requireContext(), "$mealName gegessen!", Toast.LENGTH_LONG).show()
                communicator.switchToFragment(MainFragment())

            } else {
                Toast.makeText(requireContext(), "Falsche Eingabe!", Toast.LENGTH_LONG).show()
            }

        }

        //This method validates the user input
        private fun inputCheck(mealCalories: String): Boolean {

            try {
                mealCalories.toInt()
            } catch (e: Exception) {
                return false
            }

            return true
        }




}
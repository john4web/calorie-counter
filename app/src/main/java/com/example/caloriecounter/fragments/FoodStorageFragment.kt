package com.example.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.data.StorageMeal
import com.example.caloriecounter.data.StorageMealViewModel
import com.example.caloriecounter.databinding.FragmentFoodStorageBinding

class FoodStorageFragment : Fragment() {

    private var _binding: FragmentFoodStorageBinding? = null
    private val binding get() = _binding!!

    private lateinit var communicator: ICommunicator
    private lateinit var myStorageMealViewModel: StorageMealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_food_storage, container, false)

        myStorageMealViewModel = ViewModelProvider(this).get(StorageMealViewModel::class.java)

        _binding = FragmentFoodStorageBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        binding.fragmentFoodStorageButtonAdd.setOnClickListener{
            insertStorageMealIntoDatabase()

        }

        binding.fragmentFoodStorageButtonClose.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }



        //Recyclerview storageMealList
        val storageMealAdapter = StorageMealListAdapter {
            storageMeal -> myStorageMealViewModel.deleteStorageMeal(storageMeal)
            Toast.makeText(requireContext(), "${storageMeal.name} gelöscht!", Toast.LENGTH_LONG).show()
        }
        val mealRecyclerView = binding.fragmentFoodStorageRecyclerViewStorageMealList
        mealRecyclerView.adapter = storageMealAdapter
        mealRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Viewmodel
        myStorageMealViewModel = ViewModelProvider(this).get(StorageMealViewModel::class.java)
        myStorageMealViewModel.getAllStorageMeals.observe(viewLifecycleOwner, Observer { storageMeal ->

            storageMealAdapter.setData(storageMeal)
        })




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //This method inserts the typed in meal in the database
    private fun insertStorageMealIntoDatabase(){
        val storageMealName = binding.fragmentFoodStorageInputName.text.toString()
        val storageMealCalories = binding.fragmentFoodStorageInputCalories.text.toString()

        if(inputCheck(storageMealCalories)){

            val newMeal = StorageMeal(0, storageMealName, storageMealCalories.toInt())
            myStorageMealViewModel.addStorageMeal(newMeal)

            Toast.makeText(requireContext(), "$storageMealName hinzugefügt!", Toast.LENGTH_LONG).show()

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
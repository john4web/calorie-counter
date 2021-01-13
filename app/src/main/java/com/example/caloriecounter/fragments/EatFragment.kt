package com.example.caloriecounter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.R
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
/*

        //Spinner for StorageMeals
        val storageMealSpinnerAdapter = StorageMealSpinnerAdapter()
        val mealSpinner = binding.fragmentEatSpinner
        mealSpinner.adapter = storageMealSpinnerAdapter
        mealRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Viewmodel
        myStorageMealViewModel = ViewModelProvider(this).get(StorageMealViewModel::class.java)
        myStorageMealViewModel.getAllStorageMeals.observe(viewLifecycleOwner, Observer { storageMeal ->

            storageMealAdapter.setData(storageMeal)
        })

*/
        /*
        val values = arrayOf("Time at Residence", "Under 6 months", "6-12 months", "1-2 years", "2-4 years", "4-8 years", "8-15 years", "Over 15 years")
        val adapter = ArrayAdapter(this.requireActivity(), R.layout.spinner_meal_row, values)
        adapter.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        binding.fragmentEatSpinner.adapter = adapter
*/

/*
        val spinner: Spinner = binding.fragmentEatSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                requireContext()!!,
                R.array.boroughs_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

        }
*/



        val mySpinner = binding.fragmentEatSpinner
        var spinnerStringArray = arrayListOf<String>()
        var spinnerStorageMealArray = arrayListOf<StorageMeal>()
        var selectedItem: StorageMeal? = null

        myStorageMealViewModel.getAllStorageMeals.observe(viewLifecycleOwner, Observer { storageMeal ->
           // val array = arrayOf(1, "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone","Pelican","Helix","Snake","Elephant's Trunk")

            spinnerStringArray.clear()
            spinnerStorageMealArray.clear()

            storageMeal.forEach{
                spinnerStringArray.add(it.name)
                spinnerStorageMealArray.add(it)
            }

            var adapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,spinnerStringArray)
            mySpinner.adapter=adapter

        })


        mySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectedItem = spinnerStorageMealArray[position]//parent!!.getItemAtPosition(position)
                Toast.makeText(requireContext(), selectedItem.toString(), Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        binding.fragmentEatButtonEatNowStorageMeal.setOnClickListener {
            insertStorageMealIntoDatabase(selectedItem)
        }

/*
        termsList = new ArrayList<>();
        termIdList = new ArrayList<>();
        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        mTermViewModel.getAllTerms().observe(this, new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(@Nullable final List<TermEntity> terms) {
                for (TermEntity term : terms) {
                termsList.add(term.getTermName());
                termIdList.add(term.getTermId());
            }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, termsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTermSpinner.setAdapter(adapter);
    }
        */



        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }


    private fun insertStorageMealIntoDatabase(selectedItem: StorageMeal?){

        //checken, ob selected item eh ausgewählt ist

        if(selectedItem != null) {
            val newMeal = Meal(0, selectedItem.name, selectedItem.calories)
            myMealViewModel.addMeal(newMeal)
            communicator.switchToFragment(MainFragment())
        }else{
            Toast.makeText(requireContext(), "etwas ist schiefgelaufen", Toast.LENGTH_LONG).show()

        }



    }



        //This method inserts the typed in meal in the database
        private fun insertMealIntoDatabase() {
            val mealName = binding.fragmentEatInputName.text.toString()
            val mealCalories = binding.fragmentEatInputCalories.text.toString()

            if (inputCheck(mealCalories)) {

                val newMeal = Meal(0, mealName, mealCalories.toInt())
                myMealViewModel.addMeal(newMeal)

                Toast.makeText(requireContext(), "Mahlzeit erfolgreich gespeichert", Toast.LENGTH_LONG).show()
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
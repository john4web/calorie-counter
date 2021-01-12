package com.example.caloriecounter.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriecounter.R
import com.example.caloriecounter.data.Meal
import com.example.caloriecounter.data.StorageMeal
import com.example.caloriecounter.databinding.MealRowBinding


class StorageMealListAdapter(val deleteStorageMeal: (StorageMeal) -> Unit): RecyclerView.Adapter<StorageMealListAdapter.MyViewHolder>() {

    private var storageMealList = emptyList<StorageMeal>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = MealRowBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_row, parent, false))

    }


    override fun getItemCount(): Int {
        return storageMealList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = storageMealList[position]

        with(holder){
            binding.mealRowButtonDelete.setOnClickListener {
                deleteStorageMeal(currentItem)
            }
            binding.mealRowTextViewMealName.text = currentItem.name
            binding.mealRowTextViewMealCalories.text = currentItem.calories.toString()
        }
    }



    fun setData(storageMeal: List<StorageMeal>){
        this.storageMealList = storageMeal
        notifyDataSetChanged()
    }



}
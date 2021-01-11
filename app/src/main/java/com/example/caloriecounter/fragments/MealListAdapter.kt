package com.example.caloriecounter.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriecounter.R
import com.example.caloriecounter.data.Meal
import com.example.caloriecounter.databinding.MealRowBinding


class MealListAdapter( val deleteMeal: (Meal) -> Unit): RecyclerView.Adapter<MealListAdapter.MyViewHolder>() {

    private var mealList = emptyList<Meal>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = MealRowBinding.bind(itemView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_row, parent, false))

    }


    override fun getItemCount(): Int {
        return mealList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = mealList[position]

        with(holder){
            binding.mealRowButtonDelete.setOnClickListener {
                deleteMeal(currentItem)
            }
            binding.mealRowTextViewMealName.text = currentItem.name
            binding.mealRowTextViewMealCalories.text = currentItem.calories.toString()
        }
    }



    fun setData(meal: List<Meal>){
        this.mealList = meal
        notifyDataSetChanged()
    }



}
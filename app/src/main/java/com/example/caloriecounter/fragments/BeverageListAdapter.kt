package com.example.caloriecounter.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriecounter.R
import com.example.caloriecounter.data.Beverage
import com.example.caloriecounter.databinding.BeverageRowBinding


class BeverageListAdapter(val deleteBeverage: (Beverage) -> Unit): RecyclerView.Adapter<BeverageListAdapter.MyViewHolder>() {

    private var beverageList = emptyList<Beverage>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = BeverageRowBinding.bind(itemView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.beverage_row, parent, false))

    }


    override fun getItemCount(): Int {
        return beverageList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = beverageList[position]

        with(holder){
            binding.beverageRowButtonDelete.setOnClickListener {
                deleteBeverage(currentItem)
            }
            binding.beverageRowTextViewBeverageName.text = currentItem.name
            binding.beverageRowTextViewBeverageLitres.text = currentItem.litres.toString()
        }
    }



    fun setData(beverage: List<Beverage>){
        this.beverageList = beverage
        notifyDataSetChanged()
    }



}
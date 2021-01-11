package com.example.caloriecounter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.caloriecounter.ICommunicator
import com.example.caloriecounter.R
import com.example.caloriecounter.databinding.FragmentEatBinding

class EatFragment : Fragment() {

    private var _binding: FragmentEatBinding? = null
    private val binding get() = _binding!!
    private lateinit var communicator: ICommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEatBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_eat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as ICommunicator

        binding.fragmentEatButtonEatNow.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

        binding.fragmentEatButtonCancel.setOnClickListener{
            communicator.switchToFragment(MainFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
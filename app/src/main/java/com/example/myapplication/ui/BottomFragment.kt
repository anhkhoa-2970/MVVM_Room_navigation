package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.PersonViewModel
import com.example.myapplication.R
import com.example.myapplication.data.entities.PersonItem
import com.example.myapplication.databinding.FragmentBottomBinding
import com.example.myapplication.other.PersonItemAdapter


class BottomFragment : Fragment() {
    private lateinit var binding: FragmentBottomBinding
    lateinit var personItem: PersonItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)
        val adapter = PersonItemAdapter(personViewModel)
        binding.rvPersonItem.adapter = adapter
        binding.rvPersonItem.layoutManager = LinearLayoutManager(requireContext())
        personViewModel.getAllPersonItem.observe(viewLifecycleOwner) { person ->
            adapter.setDataForAdapter(person)
        }
    }
}
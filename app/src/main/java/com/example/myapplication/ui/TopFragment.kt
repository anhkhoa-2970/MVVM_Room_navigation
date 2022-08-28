package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.PersonViewModel
import com.example.myapplication.R
import com.example.myapplication.data.entities.PersonItem
import com.example.myapplication.databinding.FragmentTopBinding


class TopFragment : Fragment() {
    private lateinit var binding: FragmentTopBinding
    private lateinit var personViewModel: PersonViewModel
    lateinit var personItem: PersonItem

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel = activity?.let {
            ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)
        }!!

        if (!personViewModel.isSaveOrUpdate) {
            binding.btnSave.setOnClickListener {
                insertDataToDatabase()
                clearText()
                it.hideKeyboard()
            }
        }
        personViewModel.selectedItem.observe(requireActivity()) { person ->
            binding.edtName.setText(person.name)
            binding.edtEmail.setText(person.email)
            var check = personViewModel.isSaveOrUpdate
            binding.btnSave.setOnClickListener {
                if (check) {
                    updatePerson(person.stt)
                    clearText()
                    check = !check
                }else{
                    insertDataToDatabase()
                    clearText()
                }
                it.hideKeyboard()
            }
        }
    }

    private fun insertDataToDatabase() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        if (checkInput(name, email)) {
            Toast.makeText(requireContext(), "Thông tin không được trống", Toast.LENGTH_SHORT)
                .show()
        } else if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
            val person = PersonItem(0, name, email)
            personViewModel.insert(person)
            Toast.makeText(requireContext(), "Nhập thông tin thành công", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Email không hợp lệ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updatePerson(stt: Int) {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        if (checkInput(name, email)) {
            Toast.makeText(requireContext(), "Thông tin không được trống", Toast.LENGTH_SHORT)
                .show()
        } else if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
            val person = PersonItem(stt, name, email)
            personViewModel.update(person)
            Toast.makeText(requireContext(), "Update thông tin thành công", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Email không hợp lệ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInput(name: String, email: String): Boolean {
        return (TextUtils.isEmpty(name) || TextUtils.isEmpty(email))
    }

    fun clearText() {
        binding.edtName.text.clear()
        binding.edtEmail.text.clear()
    }
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}
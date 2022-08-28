package com.example.myapplication.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PersonViewModel
import com.example.myapplication.data.entities.PersonItem
import com.example.myapplication.databinding.ItemPersonBinding

class PersonItemAdapter(private val personViewModel: PersonViewModel) :
    RecyclerView.Adapter<PersonItemAdapter.PersonViewHolder>() {
    private var personList: List<PersonItem> = emptyList()

    inner class PersonViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currPerson = personList[position]
        holder.binding.apply {
            tvMail.text = currPerson.email
            tvTen.text = currPerson.name

            btnSua.setOnClickListener {
                personViewModel.isSaveOrUpdate = true
                personViewModel.selectItem(currPerson)
            }

            btnXoa.setOnClickListener {
                personViewModel.delete(currPerson)
            }
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    fun setDataForAdapter(person: List<PersonItem>) {
        this.personList = person
        notifyDataSetChanged()
    }
}
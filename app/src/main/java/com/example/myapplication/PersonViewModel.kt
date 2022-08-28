package com.example.myapplication

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.data.repositories.PersonRepository
import com.example.myapplication.data.entities.PersonItem
import com.example.myapplication.data.entities.PersonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonRepository
    var isSaveOrUpdate = false
    val getAllPersonItem: LiveData<List<PersonItem>>

    private val mutableSelectedItem = MutableLiveData<PersonItem>()
    //get item from adapter for observe
    val selectedItem: LiveData<PersonItem>
        get() = mutableSelectedItem

    //get item in data in adapter, then pass to another fragment
    fun selectItem(item: PersonItem) {
        mutableSelectedItem.value = item
    }
    // init personDao to check database and call all method in personDao via method getPersonDao in PersonDatabase
    // get database
    init {
        val personDao = PersonDatabase.getDatabase(application).getPersonDao()
        repository = PersonRepository(personDao)
        // to get all item for recycle view to display ui
        getAllPersonItem = repository.getAllPersonItem
    }

    fun insert(personItem: PersonItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(personItem)
    }
    fun delete(personItem: PersonItem) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(personItem)
    }
    fun update(personItem: PersonItem)= viewModelScope.launch(Dispatchers.IO) {
        repository.update(personItem)
    }
}
//class PersonViewModel(private val repository: PersonRepository):ViewModel(){
//    var isSaveOrUpdate = false
//
//    private val mutableSelectedItem = MutableLiveData<PersonItem>()
//    //get item from adapter for observe
//    val selectedItem: LiveData<PersonItem>
//        get() = mutableSelectedItem
//
//    //get item in data in adapter, then pass to another fragment
//    fun selectItem(item: PersonItem) {
//        mutableSelectedItem.value = item
//    }
//
//    fun insert(personItem: PersonItem) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(personItem)
//    }
//    fun delete(personItem: PersonItem) = viewModelScope.launch(Dispatchers.IO){
//        repository.delete(personItem)
//    }
//    fun update(personItem: PersonItem)= viewModelScope.launch(Dispatchers.IO) {
//        repository.update(personItem)
//    }
//    fun getAllPersonItem()= repository.getAllPersonItem()
//}
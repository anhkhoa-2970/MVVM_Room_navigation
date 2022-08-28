package com.example.myapplication.data.repositories

import androidx.lifecycle.LiveData
import com.example.myapplication.data.entities.PersonDao
import com.example.myapplication.data.entities.PersonDatabase
import com.example.myapplication.data.entities.PersonItem

// contain method to interactive with table database
class PersonRepository(val personDao: PersonDao) {
    suspend fun insert(person: PersonItem) {
        personDao.insert(person)
    }
    suspend fun update(person: PersonItem){
        personDao.update(person)
    }

    suspend fun delete(person: PersonItem) {
        personDao.delete(person)
    }

    val getAllPersonItem : LiveData<List<PersonItem>> = personDao.getAllItemPerson()
}

//class PersonRepository(private val db : PersonDatabase){
//    suspend fun insert(person: PersonItem){
//        db.getPersonDao().insert(person)
//    }
//    suspend fun update(person: PersonItem){
//        db.getPersonDao().update(person)
//    }
//    suspend fun delete(person: PersonItem){
//        db.getPersonDao().delete(person)
//    }
//    fun getAllPersonItem() = db.getPersonDao().getAllItemPerson()
//}
package com.example.myapplication.data.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entities.PersonItem
@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(personItem: PersonItem)
    @Delete
    suspend fun delete(personItem: PersonItem)
    @Update
    suspend fun update(personItem: PersonItem)
    @Query("SELECT * FROM person_item")
    fun getAllItemPerson(): LiveData<List<PersonItem>>
}

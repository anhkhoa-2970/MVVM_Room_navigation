package com.example.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_item")
data class PersonItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stt")
    val stt: Int= 0,
    @ColumnInfo(name = "item_name")
    val name: String,
    @ColumnInfo(name = "item_email")
    val email: String
)
package com.example.myapplication.data.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PersonItem::class],
    exportSchema = false,
    version = 1
)
abstract class PersonDatabase : RoomDatabase() {
    // to call method add, update, delete data in databse
    abstract fun getPersonDao(): PersonDao

    companion object {
        @Volatile // all writes and reads will be done to and from the main memory intead cached memory.
        private var INSTANCE: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "person_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}
package com.example.phoneapp.data.local_db

import android.app.Application
import android.content.Context
import androidx.room.Room

object Mydb {
    fun  instance(context: Context) : AppDatabase {
       return  Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "contact-db"
        ).allowMainThreadQueries().build()
    }
}
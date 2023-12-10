package com.example.phoneapp.data

import com.example.phoneapp.data.local_db.UserDao

class Datasource(private val contactDao: UserDao) {

    fun getAll() = contactDao.getAll()
}
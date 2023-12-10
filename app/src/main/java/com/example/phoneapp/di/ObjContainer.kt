package com.example.phoneapp.di

import android.content.Context
import com.example.phoneapp.data.Datasource
import com.example.phoneapp.data.local_db.Mydb
import com.example.phoneapp.data.repo.ContacRepo
import com.example.phoneapp.views.viewModel.ContactViewModelFactory

class ObjContainer (private val applicationContext: Context){
    val dao = Mydb.instance(applicationContext).userDao()

    private val datasource = Datasource(dao)
    private val contacRepo = ContacRepo(datasource)

    val contactViewModelFactory = ContactViewModelFactory(contacRepo)

}
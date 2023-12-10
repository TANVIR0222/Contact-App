package com.example.phoneapp.data.repo

import com.example.phoneapp.data.Datasource

class ContacRepo(private val datasource: Datasource) {

    fun getAll () = datasource.getAll()


}
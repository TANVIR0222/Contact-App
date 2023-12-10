package com.example.phoneapp.views.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phoneapp.data.local_db.User
import com.example.phoneapp.data.repo.ContacRepo

class ContactViewModel (private val repository:ContacRepo): ViewModel() {

    val _responseAllNote = MutableLiveData<List<User>>()
    val responseAllNote : LiveData<List<User>> = _responseAllNote

    fun getAllNote() {

       val data =  repository.getAll()

        _responseAllNote.postValue(data)
    }
}
package com.example.phoneapp.views.viewModel

import com.example.phoneapp.data.repo.ContacRepo


interface Factory<T> {
    fun create (): T
}

class ContactViewModelFactory(private val contactReo : ContacRepo) : Factory <ContactViewModel> {
    override fun create(): ContactViewModel {
        return ContactViewModel(contactReo)
    }


}
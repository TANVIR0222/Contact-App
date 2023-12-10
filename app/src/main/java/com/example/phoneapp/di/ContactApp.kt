package com.example.phoneapp.di

import android.app.Application
import android.content.Context

class ContactApp : Application() {


    fun appContainer(context: Context): ObjContainer {

        return ObjContainer(context)

    }


}
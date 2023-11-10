package com.example.phoneapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid :Int=0,
    
    var name :String,
    
    var email:String,
    
    var phone :String
)

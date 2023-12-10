package com.example.phoneapp.data.local_db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid :Int=0,
    
    var name :String,
    
    var email:String,
    
    var phone :String
) : Parcelable

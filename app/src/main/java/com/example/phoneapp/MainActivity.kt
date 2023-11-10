package com.example.phoneapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.phoneapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , ContactAdapter.Listener{
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ContactAdapter
    lateinit var db: AppDatabase
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "contact-db"
        ).allowMainThreadQueries().build()
        
        setDataToUi()
        
        
        
        binding.button.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val phone = binding.phone.text.toString().trim()
            
            val user = User(name = name, email = email, phone = phone)
            db.userDao().insert(user)
            setDataToUi()
        }
    }
    
    private fun setDataToUi() {
        
        adapter = ContactAdapter(db.userDao().getAll(), this)
        binding.Rv.adapter = adapter
    }
    
    override fun onContactDelete(contact: User) {
        
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Are you sure?")
            .setMessage("want to delete this?")
            .setIcon(R.drawable.baseline_warning_amber_24)
            .setPositiveButton(
                "Delete"
            ) { _, _ ->
                
                
                db.userDao().delete(contact)
                Log.d("TAG", "onContactDelete: ${contact.toString()}  is deleted")
                setDataToUi()
            }
            .setNegativeButton(
                "Dismis"
            ) { _, _ -> }
        
        val alerts = alertDialog.create()
        alerts.show()
        
    }
}
package com.example.phoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.phoneapp.databinding.FragmentCreateContactBinding

class createContactFragment : Fragment() {
    lateinit var db: AppDatabase

    lateinit var binding: FragmentCreateContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateContactBinding.inflate(inflater,container,false)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "contact-db"
        ).allowMainThreadQueries().build()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.submitbtn.setOnClickListener {

            val name = binding.name.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val phone = binding.phone.text.toString().trim()

            val user = User(name = name, email = email, phone = phone)
            db.userDao().insert(user)

            findNavController().popBackStack()

        }


    }


}
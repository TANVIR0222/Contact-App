package com.example.phoneapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.phoneapp.data.local_db.Mydb
import com.example.phoneapp.data.local_db.User
import com.example.phoneapp.databinding.FragmentCreateContactBinding

class createContactFragment : Fragment() {

    lateinit var binding: FragmentCreateContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateContactBinding.inflate(inflater,container,false)


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.submitbtn.setOnClickListener {

            val name = binding.name.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val phone = binding.phone.text.toString().trim()

            val user = User(name = name, email = email, phone = phone)

            Mydb.instance(requireContext()).userDao().insert(user)
            findNavController().popBackStack()

        }


    }


}
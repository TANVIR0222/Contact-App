package com.example.phoneapp.views

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.phoneapp.data.local_db.Mydb
import com.example.phoneapp.data.local_db.User
import com.example.phoneapp.databinding.FragmentUpdateBinding

class updateFragment : Fragment() {

    lateinit var binding: FragmentUpdateBinding
    private   var contact: User? = null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateBinding.inflate(inflater,container,false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            contact = arguments?.getParcelable("contact", User::class.java)
        }else{
            contact = arguments?.getParcelable("contact")

        }
        contact?.let {

            binding.name.setText(it.name)
            binding.email.setText(it.email)
            binding.phone.setText(it.phone)

        }

        binding.submitbtn.setOnClickListener {

            val name = binding.name.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val phone = binding.phone.text.toString().trim()

            contact?.let {

                it.name = name
                it.email= email
                it.phone=phone


                Mydb.instance(requireContext()).userDao().update(it)
                findNavController().popBackStack()



            }


        }

    }


}
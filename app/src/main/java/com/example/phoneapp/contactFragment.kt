package com.example.phoneapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.phoneapp.databinding.FragmentContactBinding

class contactFragment : Fragment() ,ContactAdapter.Listener{

    lateinit var binding: FragmentContactBinding
    lateinit var adapter: ContactAdapter
    lateinit var db: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactBinding.inflate(inflater,container , false)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "contact-db"
        ).allowMainThreadQueries().build()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataToUi()

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_contactFragment_to_createContactFragment)
        }


    }

    private fun setDataToUi() {
// add adapter
        adapter = ContactAdapter(db.userDao().getAll(),this )
        binding.Rv.adapter = adapter

    }

     override fun onCreateDeleted(user: User) {


        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Are you sure")
            .setMessage("want to deleted this")
            .setIcon(R.drawable.baseline_warning_amber_24)
            .setPositiveButton("Deleted" ){_ ,_ ->

                db.userDao().delete(user)
                Log.d("TAG", "onCreateDeleted: ${user.toString()} is deleted")

                setDataToUi()

            }

            .setNegativeButton("Dismis"){_,_ ->}

        val alert = alertDialog.create()
        alert.show()

    }

    override fun onCreateUpdate(contact: User) {

        val bundle = Bundle()
        bundle.putParcelable("contact",contact)

        findNavController().navigate(R.id.action_contactFragment_to_updateFragment,bundle)

    }


}
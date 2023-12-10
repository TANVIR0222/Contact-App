package com.example.phoneapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phoneapp.R
import com.example.phoneapp.data.local_db.Mydb
import com.example.phoneapp.data.local_db.User
import com.example.phoneapp.databinding.FragmentContactBinding
import com.example.phoneapp.di.ContactApp
import com.example.phoneapp.di.ObjContainer
import com.example.phoneapp.views.Adapter.ContactAdapter
import com.example.phoneapp.views.viewModel.ContactViewModel

class contactFragment : Fragment(), ContactAdapter.Listener {

    lateinit var binding: FragmentContactBinding
    lateinit var adapter: ContactAdapter

    lateinit var viewModel: ContactViewModel

    lateinit var appContainer: ObjContainer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactBinding.inflate(inflater, container, false)

        appContainer = ContactApp().appContainer(requireContext())
        viewModel = appContainer.contactViewModelFactory.create()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataToUi()

        viewModel.getAllNote()

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_contactFragment_to_createContactFragment)
        }


    }

    private fun setDataToUi() {
// add adapter


        viewModel.responseAllNote.observe(viewLifecycleOwner){ list->

            adapter = ContactAdapter(list, this)
            binding.Rv.adapter = adapter


        }



    }

    override fun onCreateDeleted(user: User) {


        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Are you sure")
            .setMessage("want to deleted this")
            .setIcon(R.drawable.baseline_warning_amber_24)
            .setPositiveButton("Deleted") { _, _ ->

                Mydb.instance(requireContext()).userDao().delete(user)
                Log.d("TAG", "onCreateDeleted: ${user.toString()} is deleted")

                setDataToUi()

            }

            .setNegativeButton("Dismis") { _, _ -> }

        val alert = alertDialog.create()
        alert.show()

    }

    override fun onCreateUpdate(contact: User) {

        val bundle = Bundle()
        bundle.putParcelable("contact", contact)

        findNavController().navigate(R.id.action_contactFragment_to_updateFragment, bundle)

    }


}
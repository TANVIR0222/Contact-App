package com.example.phoneapp.views.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneapp.data.local_db.User
import com.example.phoneapp.databinding.ItemRvBinding
import com.example.phoneapp.views.contactFragment


class ContactAdapter(val contactList: List<User>, val listener: contactFragment) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface Listener{
        fun onCreateDeleted(user: User)
        fun onCreateUpdate(user: User)
    }


    class ContactViewHolder(val binding:ItemRvBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        
        return ContactViewHolder(
            ItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        
    }
    
    override fun getItemCount(): Int {
        return contactList.size
    }
    
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        
        contactList[position].let { contact ->
            
            holder.binding.apply {
                name.text = contact.name
                email.text = contact.email
                phone.text = contact.phone
            }

            // Long click delete // object : View.OnLongClickListener
            holder.binding.root.setOnLongClickListener (object : View.OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {

                    Log.d("TAG", "onLongClick: ${contact.uid} ")

                    listener.onCreateDeleted(contact)

                    return true
                }
            })

            holder.binding.edite.setOnClickListener {

                listener.onCreateUpdate(contact)

            }

            
        }
        
    }
    
    
}
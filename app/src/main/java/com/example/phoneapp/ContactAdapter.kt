package com.example.phoneapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneapp.databinding.ItemRvBinding


class ContactAdapter(val contactList: List<User>, val listener: ContactAdapter.Listener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    
    interface Listener {
        fun onContactDelete(user: User)
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
            
            holder.binding.root.setOnLongClickListener {
                Log.d("TAG", "long Clicked: ${contact.uid} ")
                
                listener.onContactDelete(contact)
                
                true
            }
            
            
        }
        
    }
    
    
}

package com.example.roomdatabase5.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ContacttileBinding
import com.example.roomdatabase5.activity.ContactActivity
import com.example.roomdatabase5.db.ContactEntity
import com.example.roomdatabase5.db.DBHelper

class contactAdapter(val contactList: ArrayList<ContactEntity>) : RecyclerView.Adapter<contactAdapter.contactViewHolder>() {


    class contactViewHolder(itemView: View) : ViewHolder(itemView) {
        val binding = ContacttileBinding.bind(itemView)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contacttile,parent,false)
        return contactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        holder.binding.txtName.setText("${contactList[position].name}")
        holder.binding.txtPhone.setText("${contactList[position].phone}")
        holder.binding.rvImgDelete.setOnClickListener {

            var db = DBHelper.checkDB(holder.itemView.context)
            db.dao().deleteContact(contactList[position])
            contactList.removeAt(position)
            notifyDataSetChanged()
        }
        holder.binding.rvimageEdit.setOnClickListener {

            var intent = Intent(holder.itemView.context,ContactActivity::class.java)
            intent.putExtra("name",contactList[position].name)
            intent.putExtra("phone",contactList[position].phone)
            intent.putExtra("email",contactList[position].email)
            intent.putExtra("id",contactList[position].id)
            holder.itemView.context.startActivity(intent)
        }


    }
}

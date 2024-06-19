package com.example.roomdatabase5

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase5.activity.ContactActivity
import com.example.roomdatabase5.adapter.contactAdapter
import com.example.roomdatabase5.db.ContactEntity
import com.example.roomdatabase5.db.DBHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var contactList = arrayListOf<ContactEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        binding.fbAdd.setOnClickListener {

            var intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()

        val db = DBHelper.checkDB(this)
        contactList = db.dao().getAllContacts() as ArrayList<ContactEntity>

        val dataadapter = contactAdapter(contactList as ArrayList<ContactEntity>)
        binding.rvdata.adapter = dataadapter

    }



}
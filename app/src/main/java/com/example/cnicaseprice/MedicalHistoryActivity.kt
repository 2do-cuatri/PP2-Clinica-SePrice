package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MedicalHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_medical_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button1 = findViewById<Button>(R.id.btnEdit)
        button1.setOnClickListener{
            val intent1 = Intent(this, MHEditActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnBackMH)
        button2.setOnClickListener{
            val intent2 = Intent(this, MHAccessActivity::class.java)
            startActivity(intent2)
        }

        val textName = findViewById<TextView>(R.id.txtNombreMH)
        textName.text = intent.getStringExtra("name")
        val textDni = findViewById<TextView>(R.id.txtDNIMH)
        textDni.text = intent.getStringExtra("dni")
        val textOS = findViewById<TextView>(R.id.txtSocialNMH)
        textOS.text = intent.getStringExtra("os")

        updateMHView()
    }

    private fun updateMHView() {
        val mhListView = findViewById<ListView>(R.id.listMH)
        val dbHelper= DataBase(this)
        val mhList = dbHelper.getAllMH(intent.getStringExtra("dni")!!)
        val adapter = object : ArrayAdapter<MHEntry>(this, 0, mhList) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.mh_list_item, parent, false) // Replace with your item layout
                val item = getItem(position) // Get the MHEntry object for the current position

                val dateTextView = view.findViewById<TextView>(R.id.dateTextView) // Replace with your view IDs
                val doctorTextView = view.findViewById<TextView>(R.id.doctorTextView) // Replace with your view IDs
                val detailTextView = view.findViewById<TextView>(R.id.detailTextView)

                dateTextView.text = item?.date
                doctorTextView.text = item?.doctor
                detailTextView.text = item?.detail

                return view
            }
        }
        mhListView.adapter = adapter
    }

}
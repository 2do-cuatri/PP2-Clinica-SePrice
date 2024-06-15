package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

//        updateMHView()
    }

//    private fun updateMHView() {
//        TODO("Not yet implemented")
//    }
}
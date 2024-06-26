package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DoctorMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doctor_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button1 = findViewById<Button>(R.id.btnPatientMed)
        button1.setOnClickListener{
            val intent1 = Intent(this, DoctorPatientsActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnSupplies)
        button2.setOnClickListener{
            val intent2 = Intent(this, SuppliesActivity::class.java)
            startActivity(intent2)
        }
        val button3 = findViewById<Button>(R.id.btnBackMainDM)
        button3.setOnClickListener{
            val intent3 = Intent(this, MainActivity::class.java)
            startActivity(intent3)
       }
    }
}
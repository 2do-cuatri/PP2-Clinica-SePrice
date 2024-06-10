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
        val patientButton = findViewById<Button>(R.id.btnPatientMed)
        val suppliesButton = findViewById<Button>(R.id.btnSupplies)

        suppliesButton.setOnClickListener {
            var intent = Intent(this, SuppliesActivity::class.java)
            startActivity(intent)
        }

        patientButton.setOnClickListener {
            var intent = Intent(this, PatientActivity::class.java)
        }
    }
}
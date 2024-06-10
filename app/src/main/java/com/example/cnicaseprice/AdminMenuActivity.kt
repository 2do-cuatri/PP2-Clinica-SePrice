package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AdminMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_menu)
        val button3 = findViewById<Button>(R.id.btnPatientAdm)
        button3.setOnClickListener{
            val intent3 = Intent(this, AdmissionActivity::class.java)
            startActivity(intent3)
        }
        val button4 = findViewById<Button>(R.id.btnStudies)
        button4.setOnClickListener{
            val intent4 = Intent(this, StudiesActivity::class.java)
            startActivity(intent4)
        }
        val button5 = findViewById<Button>(R.id.btnLaboratory)
        button5.setOnClickListener{
            val intent5 = Intent(this, LaboratoryActivity::class.java)
            startActivity(intent5)
        }
        val button6 = findViewById<Button>(R.id.btnClinic)
        button6.setOnClickListener{
            val intent6 = Intent(this, ClinicActivity::class.java)
            startActivity(intent6)
        }
    }
}
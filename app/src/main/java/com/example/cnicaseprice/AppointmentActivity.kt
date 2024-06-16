package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AppointmentActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        try {
            databaseHelper = DataBase(this)
            val lvStudies = findViewById<ListView>(R.id.lvStudies)
            val studies = databaseHelper.getAllStudies()
            val studiesAdapter = StudyAdapter(studies, this)
            lvStudies.adapter = studiesAdapter
        } catch(e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        val btnBack = findViewById<Button>(R.id.btnBackAppointments)
        btnBack.setOnClickListener{
            val intent5 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent5)
        }
    }
}
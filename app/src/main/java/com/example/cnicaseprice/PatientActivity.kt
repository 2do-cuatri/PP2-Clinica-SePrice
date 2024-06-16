package com.example.cnicaseprice


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PatientActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nomPat = findViewById<TextView>(R.id.txtNomPac)
        val dniPat = findViewById<TextView>(R.id.txtDniPac)
        val osPat = findViewById<TextView>(R.id.txtOsPac)

        nomPat.text = intent.getStringExtra("name")
        dniPat.text = intent.getStringExtra("dni")
        osPat.text = intent.getStringExtra("os")

        try {
            databaseHelper = DataBase(this)
            val appointments = databaseHelper.getPatientAppointments(intent.getStringExtra("dni"))
            val lvAppointments = findViewById<ListView>(R.id.lvAppointments)
            val appointmentsAdapter = AppointmentAdapter(appointments, this)
            lvAppointments.adapter = appointmentsAdapter
        } catch(ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        }
        val btnBack = findViewById<Button>(R.id.btnBackMain)
        btnBack.setOnClickListener{
            val intent2 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent2)
        }


    }
}
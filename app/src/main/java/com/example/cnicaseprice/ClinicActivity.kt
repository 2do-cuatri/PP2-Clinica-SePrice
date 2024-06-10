package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ClinicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clinic)

        val button10 = findViewById<Button>(R.id.btnAppointment)
        button10.setOnClickListener{
            val intent10 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent10)
        }
        val button11 = findViewById<Button>(R.id.btnAppointmentO)
        button11.setOnClickListener{
            val intent11 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent11)
        }
    }
}
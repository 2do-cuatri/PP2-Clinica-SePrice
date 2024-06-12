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

        val button1 = findViewById<Button>(R.id.btnAppointment)
        button1.setOnClickListener{
            val intent1 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnAppointmentO)
        button2.setOnClickListener{
            val intent2 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent2)
        }
        val button3 = findViewById<Button>(R.id.btnBackAdmMenuC)
        button3.setOnClickListener{
            val intent3 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent3)
        }
    }
}
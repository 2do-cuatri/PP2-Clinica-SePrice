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
        val button1 = findViewById<Button>(R.id.btnPatientAdm)
        button1.setOnClickListener{
            val intent1 = Intent(this, AdmissionActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnStudies)
        button2.setOnClickListener{
            val intent2 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent2)
        }
        val button3 = findViewById<Button>(R.id.btnLaboratory)
        button3.setOnClickListener{
            val intent3 = Intent(this, StudyVoucherActivity::class.java)
            startActivity(intent3)
        }
        val button4 = findViewById<Button>(R.id.btnBackMain)
        button4.setOnClickListener{
            val intent4 = Intent(this, MainActivity::class.java)
            startActivity(intent4)
        }
    }
}
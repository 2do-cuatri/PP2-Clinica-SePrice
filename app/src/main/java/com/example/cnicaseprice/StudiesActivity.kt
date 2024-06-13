package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class StudiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_studies)

        val button1 = findViewById<Button>(R.id.btnAppointmentS)
        button1.setOnClickListener{
            val intent1 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnVoucher)
        button2.setOnClickListener{
            val intent2 = Intent(this, StudyVoucherActivity::class.java)
            startActivity(intent2)
        }
        val button3 = findViewById<Button>(R.id.btnBackAdmMenuE)
        button3.setOnClickListener{
            val intent3 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent3)
        }
    }
}
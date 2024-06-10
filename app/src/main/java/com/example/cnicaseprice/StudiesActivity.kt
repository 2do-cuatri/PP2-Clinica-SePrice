package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_studies)

        val button1 = findViewById<Button>(R.id.btnAppointmentS)
        button1.setOnClickListener{
            val intent1 = Intent(this, StudyActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnBackAdmMenuE)
        button2.setOnClickListener{
            val intent2 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent2)
        }
    }
}
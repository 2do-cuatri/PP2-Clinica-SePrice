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

        val button8 = findViewById<Button>(R.id.btnAppointmentS)
        button8.setOnClickListener{
            val intent8 = Intent(this, StudyActivity::class.java)
            startActivity(intent8)
        }
    }
}
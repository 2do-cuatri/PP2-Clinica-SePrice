package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LaboratoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_laboratory)

        val button9 = findViewById<Button>(R.id.btnAppointmentL)
        button9.setOnClickListener{
            val intent9 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent9)
        }
    }
}
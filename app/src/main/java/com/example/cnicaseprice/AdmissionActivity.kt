package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AdmissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admission)

        val button7 = findViewById<Button>(R.id.btnFindPatient)
        button7.setOnClickListener{
            val intent7 = Intent(this, PatientActivity::class.java)
            startActivity(intent7)
        }
    }
}
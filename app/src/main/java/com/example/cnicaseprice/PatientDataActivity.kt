package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PatientDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button1 = findViewById<Button>(R.id.btnContinueCM)
        button1.setOnClickListener{
            val intent1 = Intent(this, ConfirmMessageActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnBackApp)
        button2.setOnClickListener{
            val intent2 = Intent(this, AppointmentActivity::class.java)
            startActivity(intent2)
        }
    }
}
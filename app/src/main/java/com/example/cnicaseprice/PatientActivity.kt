package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class PatientActivity : AppCompatActivity() {
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


    }
}
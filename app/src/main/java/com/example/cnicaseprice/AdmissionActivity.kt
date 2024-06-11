package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AdmissionActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admission)

        val button1 = findViewById<Button>(R.id.btnFindPatient)
        button1.setOnClickListener{
            val dni = findViewById<EditText>(R.id.txtDni)
            val os = findViewById<EditText>(R.id.txtSocialNumber)
            try {
                databaseHelper = DataBase(this)
                val patient = databaseHelper.getPatient(dni.text.toString(), os.text.toString())
                if (patient.DNI.isNotEmpty()) {
                    val intent1 = Intent(this, PatientActivity::class.java)
                    intent1.putExtra("dni", patient.DNI)
                    intent1.putExtra("name", patient.Nombre + patient.Apellido)
                    intent1.putExtra("os", patient.OS)
                    startActivity(intent1)
                }
            } catch(e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        val button2 = findViewById<Button>(R.id.btnBackAdmMenu)
        button2.setOnClickListener{
            val intent2 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent2)
        }

    }
}
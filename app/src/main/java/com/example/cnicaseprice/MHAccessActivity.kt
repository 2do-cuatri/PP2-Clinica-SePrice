package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MHAccessActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mhacces)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val continueButton = findViewById<Button>(R.id.btnContinue)
        continueButton.setOnClickListener{
            val dni = findViewById<EditText>(R.id.txtDni)
            try {
                databaseHelper = DataBase(this)
                val patient = databaseHelper.getPatient(dni.text.toString())
                if (patient.DNI.isNotEmpty()) {
                    val intent1 = Intent(this, MedicalHistoryActivity::class.java)
                    intent1.putExtra("dni", patient.DNI)
                    intent1.putExtra("name", patient.Nombre + patient.Apellido)
                    intent1.putExtra("os", patient.OS)
                    startActivity(intent1)
                }
            } catch(e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        //  val intent1 = Intent(this, MedicalHistoryActivity::class.java)

        }
        val button2 = findViewById<Button>(R.id.btnBackDP)
        button2.setOnClickListener{
            val intent2 = Intent(this, DoctorPatientsActivity::class.java)
            startActivity(intent2)
        }
    }
}
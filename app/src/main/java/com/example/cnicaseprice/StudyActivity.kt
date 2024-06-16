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

class StudyActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_study)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val studyId = intent.getIntExtra("studyId", 0)
        val btnContinue = findViewById<Button>(R.id.btnSetAppointment)
        btnContinue.setOnClickListener{
            databaseHelper = DataBase(this)
            val date = findViewById<EditText>(R.id.txtDate).text.toString()
            val time = findViewById<EditText>(R.id.txtTime).text.toString()
            var appointmentId: Long? = null;
            try {
                appointmentId = databaseHelper.createAppointment(studyId.toString(), "$date $time")
            } catch (ex: Exception) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
            if (appointmentId !== null) {
                try {
                    val dni = findViewById<EditText>(R.id.txtDni).text?.toString()
                    val os = findViewById<EditText>(R.id.txtSocialNumber).text?.toString()
                    val firstName = findViewById<EditText>(R.id.txtFirstName).text?.toString()
                    val lastName = findViewById<EditText>(R.id.txtLastName).text?.toString()
                    databaseHelper.assignAppointmentToPatient(appointmentId.toInt(), dni, os, firstName, lastName)
                    val confirmIntent = Intent(this, ConfirmMessageActivity::class.java)
                    confirmIntent.putExtra("appId", appointmentId.toString())
                    startActivity(confirmIntent)
                } catch(e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        val btnBack = findViewById<Button>(R.id.btnBackAppointments)
        btnBack.setOnClickListener{
            val intentBack = Intent(this, AppointmentActivity::class.java)
            startActivity(intentBack)
        }

    }
}
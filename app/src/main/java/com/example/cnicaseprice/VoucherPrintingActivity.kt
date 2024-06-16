package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VoucherPrintingActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_voucher_printing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtVoucherInfo = findViewById<TextView>(R.id.txtVoucherInfo)
        val appId = intent.getStringExtra("appId").toString();
        if (appId.isNotEmpty()) {
            try {
                databaseHelper = DataBase(this)
                val appData = databaseHelper.getFullAppointmentData(appId);
                txtVoucherInfo.text = buildString {
                    append("Paciente: ${appData.patient.Nombre} ${appData.patient.Apellido} \n")
                    append("DNI: ${appData.patient.DNI} \n")
                    append("Nro de obra social: ${appData.patient.OS} \n\n")
                    append("Asistio a ${appData.appointment.Name} en el area de ${appData.appointment.Type} ")
                    append("el dia ${appData.appointment.Date}")
                }
            } catch(e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        val button1 = findViewById<Button>(R.id.btnBackSV)
        button1.setOnClickListener{
            val intent1 = Intent(this, StudyVoucherActivity::class.java)
            startActivity(intent1)
        }
        val button2 = findViewById<Button>(R.id.btnVoucherPrinting)
        button2.setOnClickListener {
            Toast.makeText(this, "Comprobante enviado a dispositivo de impresion", Toast.LENGTH_SHORT).show()
        }
    }
}
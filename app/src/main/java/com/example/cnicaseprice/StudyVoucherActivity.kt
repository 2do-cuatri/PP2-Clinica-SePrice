package com.example.cnicaseprice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudyVoucherActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_study_voucher)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCode = findViewById<EditText>(R.id.txtCode);
        val button1 = findViewById<Button>(R.id.btnVoucher)
        button1.setOnClickListener{
            try {
                databaseHelper = DataBase(this)
                val input = txtCode.text.toString()
                if (databaseHelper.getAppointmentExists(input)) {
                    val intent1 = Intent(this, VoucherPrintingActivity::class.java)
                    intent1.putExtra("appId", input)
                    startActivity(intent1)
                } else {
                    throw Exception("Estudio no encontrado")
                }
            } catch(ex: Exception) {

            }
        }
        val button2 = findViewById<Button>(R.id.btnBackS)
        button2.setOnClickListener{
            val intent2 = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent2)
        }
    }
}
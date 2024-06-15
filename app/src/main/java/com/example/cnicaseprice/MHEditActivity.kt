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

class MHEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mhedit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button1 = findViewById<Button>(R.id.btnSave)
        button1.setOnClickListener{
            val editText = findViewById<EditText>(R.id.editEntry)

            if (editText.text.isEmpty()) {
                Toast.makeText(this, "No puede crear etradas vacias", Toast.LENGTH_SHORT).show()
            }else{
                val entry = editText.text.toString()
                val db = DataBase(this)
                db.addMHEntry(intent.getStringExtra("dni")!!, "Javier Rodriguez", entry)
                val intent1 = Intent(this, MedicalHistoryActivity::class.java)
                startActivity(intent1)
            }

        }
        val button2 = findViewById<Button>(R.id.btnCancel)
        button2.setOnClickListener{
            val intent2 = Intent(this, MedicalHistoryActivity::class.java)
            startActivity(intent2)
        }
    }
}
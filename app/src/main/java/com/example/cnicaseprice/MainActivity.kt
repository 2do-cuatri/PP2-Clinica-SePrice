package com.example.cnicaseprice

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Conectar base de datos.
        val con= BaseDatos(this)

        //Crear credenciales de admin por única vez
        val sharedPreferences = getSharedPreferences("MyAdminCredentials", Context.MODE_PRIVATE)
        val credentialsCreated = sharedPreferences.getBoolean("credentials_created", false)

        if (!credentialsCreated) {
            con.insertarUsuario("admin", "admin", "admin")
            sharedPreferences.edit().putBoolean("credentials_created", true).apply()
        } else {
            Toast.makeText(this, "Admin ya está creado", Toast.LENGTH_SHORT).show()
        }
    }
}
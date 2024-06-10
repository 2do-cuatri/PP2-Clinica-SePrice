package com.example.cnicaseprice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



private lateinit var insumo_a_card: ToggleCard
private lateinit var insumo_a_text: TextView
private lateinit var insumo_b_card: ToggleCard
private lateinit var insumo_b_text: TextView
private lateinit var insumo_c_card: ToggleCard
private lateinit var insumo_c_text: TextView
private lateinit var insumo_d_card: ToggleCard
private lateinit var insumo_d_text: TextView

private var stockInsumos= intArrayOf(0, 0, 0, 0)

class SuppliesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_supplies)


        insumo_a_card = findViewById(R.id.insumo_a_card)
        insumo_a_card.setOnClickListener {
            insumo_a_card.setChecked(!insumo_a_card.isChecked())
        }
        insumo_a_text = findViewById(R.id.cant_insumo_a_txt)


        insumo_b_card = findViewById(R.id.insumo_b_card)
        insumo_b_card.setOnClickListener {
            insumo_b_card.setChecked(!insumo_b_card.isChecked())
        }
        insumo_b_text = findViewById(R.id.cant_insumo_b_txt)


        insumo_c_card = findViewById(R.id.insumo_c_card)
        insumo_c_card.setOnClickListener {
            insumo_c_card.setChecked(!insumo_c_card.isChecked())
        }
        insumo_c_text = findViewById(R.id.cant_insumo_c_txt)


        insumo_d_card = findViewById(R.id.insumo_d_card)
        insumo_d_card.setOnClickListener {
            insumo_d_card.setChecked(!insumo_d_card.isChecked())
        }
        insumo_d_text = findViewById(R.id.cant_insumo_d_txt)

        updateStock()

        var restockButton = findViewById<Button>(R.id.restock_btn)
        restockButton.setOnClickListener {
            stockInsumos = intArrayOf(50, 50, 50, 50)
            updateStock()
        }

        var useSuppliesBtn = findViewById<Button>(R.id.use_selected_btn)
        useSuppliesBtn.setOnClickListener {
            var error = useSelectedInsumos()
            if (error == null) {
                updateStock()
            } else {
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun updateStock() {
        insumo_a_text.text = stockInsumos[0].toString() + "/50"
        insumo_b_text.text = stockInsumos[1].toString() + "/50"
        insumo_c_text.text = stockInsumos[2].toString() + "/50"
        insumo_d_text.text = stockInsumos[3].toString() + "/50"
    }

    fun useSelectedInsumos(): Error? {
        var error: Error? = null
        var nuevoStock = intArrayOf(0, 0, 0, 0)
        if (insumo_a_card.isChecked()) {
            if (stockInsumos[0] > 0) {
                nuevoStock[0] = stockInsumos[0]-1
            } else{
                error = Error("No hay stock de todos los productos seleccionados")
            }
        } else{
            nuevoStock[0] = stockInsumos[0]
        }



        if (insumo_b_card.isChecked()) {
            if (stockInsumos[1] > 0) {
                nuevoStock[1] = stockInsumos[1]-1
            } else {
                error = Error("No hay stock de todos los productos seleccionados")
            }
        } else {
            nuevoStock[1] = stockInsumos[1]
        }



        if (insumo_c_card.isChecked()) {
            if (stockInsumos[2] > 0) {
                nuevoStock[2] = stockInsumos[2] - 1
            } else {
                error = Error("No hay stock de todos los productos seleccionados")
            }
        } else {
            nuevoStock[2] = stockInsumos[2]
        }


        if (insumo_d_card.isChecked()) {
            if (stockInsumos[3] > 0) {
                nuevoStock[3] = stockInsumos[3]-1
            } else {
                error = Error("No hay stock de todos los productos seleccionados")
            }
        } else {
            nuevoStock[3] = stockInsumos[3]
        }


        if (error == null) {
            stockInsumos = nuevoStock
        }
        return error
    }
}
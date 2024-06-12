package com.example.cnicaseprice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private var stock = mutableListOf<Supply>()
private var selectedSupplies = arrayOf(false, false, false, false, false, false)
private var supplyButtons= mutableListOf<Button>()


class SuppliesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_supplies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val requestButton = findViewById<Button>(R.id.btnRequest)
        requestButton.setOnClickListener{
            reStock()
            Toast.makeText(this, "Reposición de insumos completa", Toast.LENGTH_SHORT).show()
        }
        val backButton = findViewById<Button>(R.id.btnBackDMS)
        backButton.setOnClickListener{
            val intent2 = Intent(this, DoctorMenuActivity::class.java)
            startActivity(intent2)
        }

        val useSuppliesButton = findViewById<Button>(R.id.btnUse)
        useSuppliesButton.setOnClickListener{
            val error = useSelectedSupplies()
            if(error != null) {
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            } else {
                updateButtonsText()
            }
        }

        // Inicializar stock
        val db = DataBase(this)
        stock = db.getSupplies()


        supplyButtons.add(findViewById(R.id.btnSupply1))
        supplyButtons.add(findViewById(R.id.btnSupply2))
        supplyButtons.add(findViewById(R.id.btnSupply3))
        supplyButtons.add(findViewById(R.id.btnSupply4))
        supplyButtons.add(findViewById(R.id.btnSupply5))
        supplyButtons.add(findViewById(R.id.btnSupply6))

        supplyButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                selectedSupplies[index] = !selectedSupplies[index]
                if (selectedSupplies[index]) {
                    button.setBackgroundColor(Color.parseColor("#6591B3"))
                } else {
                    button.setBackgroundColor(Color.parseColor("#DACACA"))
                }
            }
        }

        updateButtonsText()


    }

    private fun updateButtonsText(){
        stock.forEachIndexed { index, insumo ->
            supplyButtons[index].text = buildString {
                append(insumo.name)
                append("\n")
                append(insumo.quantity)
                append("/50")
            }
        }
    }

    private fun reStock(){
        stock.forEach {
            it.quantity = 50
        }
        val db = DataBase(this)
        db.updateSupplies(stock)
        updateButtonsText()
    }

    private fun useSelectedSupplies(): Error? {
        var error: Error? = null
        var newStock = stock.toMutableList()
        var somethingSelected = false
        supplyButtons.forEachIndexed { index, _ ->
            if (selectedSupplies[index]) {
                somethingSelected = true
                if(stock[index].quantity > 0) {
                    newStock[index].quantity = stock[index].quantity - 1
                } else{
                    error = Error("No hay stock suficiente de todos los elementos seleccionados")
                }
            }
        }
        if(!somethingSelected){
            error = Error("No hay insumos seleccionados")
        }
        if(error == null){
            stock = newStock.toMutableList()
            val db = DataBase(this)
            db.updateSupplies(stock)
        }
        return error
    }
}

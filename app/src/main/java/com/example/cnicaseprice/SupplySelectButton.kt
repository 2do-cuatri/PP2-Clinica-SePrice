package com.example.cnicaseprice

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton


class SupplySelectButton@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        // Set a click listener
        setOnClickListener {
            // Perform some action when the button is clicked
            println("Custom button clicked!")
        }
    }
}
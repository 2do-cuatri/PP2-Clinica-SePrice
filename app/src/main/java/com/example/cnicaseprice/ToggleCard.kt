package com.example.cnicaseprice

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class ToggleCard: CardView {

    private var isChecked: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleCard)
        isChecked = typedArray.getBoolean(R.styleable.ToggleCard_isChecked, false)
        typedArray.recycle()
    }

    init {
        background = ContextCompat.getDrawable(context, R.drawable.toggle_off_background)
    }
    fun isChecked(): Boolean {
        return isChecked
    }

    fun setChecked(checked: Boolean) {
        isChecked = checked
        // Update the UI based on the checked state
        background = if (isChecked) {
            ContextCompat.getDrawable(context, R.drawable.toggle_on_background)
        } else {
            ContextCompat.getDrawable(context, R.drawable.toggle_off_background)
        }
    }
}
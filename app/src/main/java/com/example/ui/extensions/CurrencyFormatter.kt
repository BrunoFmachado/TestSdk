package com.example.ui.extensions

import java.text.NumberFormat
import java.util.*

object CurrencyFormatter {
    private val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    fun formatFromCents(value: String, divisor: Double = 100.0): String {
        val number = value.toLongOrNull() ?: 0L
        return format.format(number / divisor)
    }
}

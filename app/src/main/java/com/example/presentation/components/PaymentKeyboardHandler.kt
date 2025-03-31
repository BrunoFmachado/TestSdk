package com.example.presentation.components

class PaymentKeyboardHandler(
    private val onInputChanged: (String) -> Unit
) {
    private var rawInput = ""

    fun onKeyPressed(value: String) {
        rawInput += value
        onInputChanged(rawInput)
    }

    fun onDelete() {
        rawInput = rawInput.dropLast(1)
        onInputChanged(rawInput)
    }

    fun reset() {
        rawInput = ""
        onInputChanged(rawInput)
    }

    fun getRawValue(): String = rawInput
}

package com.example.presentation.state

sealed class PaymentFeedback {
    object Loading : PaymentFeedback()
    data class Success(val link: String) : PaymentFeedback()
    data class Error(val message: String) : PaymentFeedback()
    object None : PaymentFeedback()
}

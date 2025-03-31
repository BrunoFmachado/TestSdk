package com.example.presentation.state

sealed class PaymentUiState {
    object Idle : PaymentUiState()
    object Loading : PaymentUiState()
    data class Success(val initPoint: String) : PaymentUiState()
    data class Error(val message: String) : PaymentUiState()
}

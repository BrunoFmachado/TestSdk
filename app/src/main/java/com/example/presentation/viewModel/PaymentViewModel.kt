package com.example.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.PreferenceUseCase
import com.example.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val createPreferenceUseCase: PreferenceUseCase
) : ViewModel() {

    private val uiStateFlow = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState = uiStateFlow.asStateFlow()

    fun createPreference(title: String, value: Float) {
        viewModelScope.launch {
            uiStateFlow.value = UiState.Loading
            try {
                val result = createPreferenceUseCase(title, value)
                uiStateFlow.value = UiState.Success(result.initPoint)
            } catch (e: Exception) {
                uiStateFlow.value = UiState.Error(e.localizedMessage ?: "Erro desconhecido")
            }
        }
    }
}

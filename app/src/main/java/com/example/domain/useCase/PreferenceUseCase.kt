package com.example.domain.useCase

import com.example.data.model.Item
import com.example.data.model.PreferenceRequest
import com.example.data.model.PreferenceResponse
import com.example.domain.repository.PaymentRepository
import javax.inject.Inject

class PreferenceUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    suspend operator fun invoke(title: String, value: Float): PreferenceResponse {
        val safeTitle = title.ifBlank { "Produto sem descrição" }
        val safeValue = if (value <= 0f) 1f else value
        val item = Item(title = safeTitle, unit_price = safeValue)
        val request = PreferenceRequest(listOf(item))
        return repository.createPreference(request)
    }
}

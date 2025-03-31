package com.example.data.dataRepository

import com.example.data.model.PreferenceRequest
import com.example.data.model.PreferenceResponse
import com.example.data.remote.MercadoPagoService
import com.example.domain.repository.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val service: MercadoPagoService
) : PaymentRepository {
    override suspend fun createPreference(request: PreferenceRequest): PreferenceResponse {
        val response = service.createPreference(request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Resposta vazia")
        } else {
            throw Exception("Erro na API: ${response.errorBody()?.string()}")
        }
    }
}
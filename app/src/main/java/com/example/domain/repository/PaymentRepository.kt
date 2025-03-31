package com.example.domain.repository

import com.example.data.model.PreferenceRequest
import com.example.data.model.PreferenceResponse

interface PaymentRepository {
    suspend fun createPreference(request: PreferenceRequest): PreferenceResponse
}

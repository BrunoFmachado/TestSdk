package com.example.data.remote

import com.example.data.model.PreferenceRequest
import com.example.data.model.PreferenceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MercadoPagoService {
    @POST("checkout/preferences")
    suspend fun createPreference(@Body body: PreferenceRequest): Response<PreferenceResponse>
}
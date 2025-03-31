package com.example.data.model

import com.google.gson.annotations.SerializedName

data class PreferenceResponse(
    @SerializedName("init_point")
    val initPoint: String
)
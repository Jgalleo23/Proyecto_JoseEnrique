package com.joseenrique.proyecto_joseenrique.data.API

import com.google.gson.annotations.SerializedName

data class ApiRespuesta(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)
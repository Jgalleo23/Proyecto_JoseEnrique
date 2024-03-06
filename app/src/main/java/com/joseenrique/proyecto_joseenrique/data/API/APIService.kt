package com.joseenrique.proyecto_joseenrique.data.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPerrosPorRaza(@Url url: String): Response<ApiRespuesta>
}
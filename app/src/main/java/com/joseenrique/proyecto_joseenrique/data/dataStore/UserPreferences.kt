package com.joseenrique.proyecto_joseenrique.data.dataStore

data class UserPreferences(
    val nombre: String,
    val contrasena: String,
    val introduccion: Boolean,
    val descripcion: String,
    val sexo: String,
    val raza: String
)
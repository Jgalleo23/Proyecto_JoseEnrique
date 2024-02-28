package com.joseenrique.proyecto_joseenrique.data.Usuario

data class Usuario (
    val nombre: String,
    val descripcion: String,
    val sexo: String,
    val contrasena: String,
    val nombreMascota: String,
    val foto: String,
    val fotoPerfil: String,
    var isFavorite: Boolean = false
)
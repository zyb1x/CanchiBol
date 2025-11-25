package com.example.canchibol.domain.entity

data class PartidoEntity(
    val titular: String,
    val equipo1: String,
    val equipo2: String,
    val fecha: String, // Formato YYYY-MM-DD
    val hora: String, // Formato HH:MM:SS
    val cancha: String,
    val arbitro: String? = null
)
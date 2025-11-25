
package com.example.canchibol.data.network.dto

import com.google.gson.annotations.SerializedName

data class PartidoResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("partidos") val partidos: List<PartidoDto> = emptyList(),
    @SerializedName("message") val message: String? = null
)

data class PartidoDto(
    @SerializedName("titular") val titular: String = "",
    @SerializedName("equipo1") val equipo1: String = "",
    @SerializedName("equipo2") val equipo2: String = "",
    @SerializedName("fecha") val fecha: String = "",
    @SerializedName("hora") val hora: String = "",
    @SerializedName("cancha") val cancha: String = "",
    // NUEVO CAMPO OPCIONAL
    @SerializedName("arbitro") val arbitro: String? = null
)
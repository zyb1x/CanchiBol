package com.example.canchibol.data.repository

import com.example.canchibol.domain.entity.PartidoEntity

interface PartidoRepository {
    suspend fun getPartidosPorFecha(fecha: String): Result<List<PartidoEntity>>
    suspend fun getProximosPartidos(): Result<List<PartidoEntity>>
}
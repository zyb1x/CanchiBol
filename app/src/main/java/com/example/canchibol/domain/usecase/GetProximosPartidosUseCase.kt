package com.example.canchibol.domain.usecase

import com.example.canchibol.data.repository.PartidoRepository
import com.example.canchibol.domain.entity.PartidoEntity

class GetProximosPartidosUseCase(private val partidoRepository: PartidoRepository) {
    suspend operator fun invoke(): Result<List<PartidoEntity>> {
        return partidoRepository.getProximosPartidos()
    }
}
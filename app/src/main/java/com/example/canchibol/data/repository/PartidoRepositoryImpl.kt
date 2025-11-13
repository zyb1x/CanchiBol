// data/repository/PartidoRepositoryImpl.kt
package com.example.canchibol.data.repository

import android.content.Context
import com.example.canchibol.data.network.ApiService
import com.example.canchibol.data.network.dto.PartidoDto
import com.example.canchibol.domain.entity.PartidoEntity
import com.example.canchibol.data.repository.PartidoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset


class PartidoRepositoryImpl (
    private val context: Context
) : PartidoRepository {

    private fun cleanText(text: String): String {
        return try {
            // Si el texto ya está bien, no hacer nada
            if (text.toByteArray(Charsets.UTF_8).contentEquals(text.toByteArray(Charsets.ISO_8859_1))) {
                return text
            }
            String(text.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)
        } catch (e: Exception) {
            println("DEBUG: Error cleaning text '$text': ${e.message}")
            text
        }
    }

    private fun PartidoDto.toEntity(): PartidoEntity {
        return PartidoEntity(
            titular = cleanText(titular),
            equipo1 = cleanText(equipo1),
            equipo2 = cleanText(equipo2),
            fecha = fecha,
            hora = hora,
            cancha = cleanText(cancha)
        )
    }

    override suspend fun getPartidosPorFecha(fecha: String): Result<List<PartidoEntity>> {
        return try {
            withContext(Dispatchers.IO) {
                val result = ApiService.getPartidosPorFecha(context, fecha)

                if (result.isSuccess) {
                    val response = result.getOrNull()
                    if (response?.success == true) {
                        val partidos = response.partidos.map { it.toEntity() }
                        println("DEBUG: Partidos convertidos: $partidos")
                        Result.success(partidos)
                    } else {
                        Result.failure(Exception(response?.message ?: "Error al obtener partidos"))
                    }
                } else {
                    Result.failure(result.exceptionOrNull() ?: Exception("Error de conexión"))
                }
            }
        } catch (e: Exception) {
            println("DEBUG: Repository error: ${e.message}")
            Result.failure(Exception("Error: ${e.message}"))
        }
    }
}
package com.example.canchibol.domain.repository



import com.example.canchibol.domain.entity.UserEntity


interface AuthRepository {

    suspend fun login(email: String, password: String): Result<UserEntity>

    /**
     * Registra un nuevo usuario
     */
    suspend fun register(
        email: String,
        password: String,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String
    ): Result<UserEntity>

    /**
     * Cierra la sesión del usuario actual
     */
    suspend fun logout(): Result<Unit>

    /**
     * Verifica si hay una sesión activa
     */
    suspend fun isUserLoggedIn(): Boolean
}
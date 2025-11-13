package com.example.canchibol.domain.repository



import com.example.canchibol.domain.entity.UserEntity


interface AuthRepository {

    suspend fun login(email: String, password: String): Result<UserEntity>


    suspend fun register(
        email: String,
        password: String,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String
    ): Result<UserEntity>


    suspend fun logout(): Result<Unit>


    suspend fun isUserLoggedIn(): Boolean
}
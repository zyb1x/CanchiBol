package com.example.canchibol.domain.usecase



import android.util.Patterns
import com.example.canchibol.domain.entity.UserEntity
import com.example.canchibol.domain.repository.AuthRepository


class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<UserEntity> {
        // Validación 1: Campos vacíos
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Completa todos los campos"))
        }

        // Validación 2: Formato de email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("El formato del email es inválido"))
        }

        // Validación 3: Longitud mínima de contraseña
        if (password.length < 5) {
            return Result.failure(Exception("La contraseña debe tener al menos 5 caracteres"))
        }

        // Delegar la operación al repository
        return try {
            authRepository.login(email, password)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
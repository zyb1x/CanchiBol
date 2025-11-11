package com.example.canchibol.domain.usecase

import android.util.Patterns
import com.example.canchibol.domain.entity.UserEntity
import com.example.canchibol.domain.repository.AuthRepository


class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        email: String,
        password: String,
        confirmPassword: String,
        noEmpleado: String
    ): Result<UserEntity> {

        // Validación Campos obligatorios
        if (nombre.isBlank()) {
            return Result.failure(Exception("El nombre es obligatorio"))
        }

        if (email.isBlank()) {
            return Result.failure(Exception("El correo es obligatorio"))
        }

//        if (noEmpleado.isBlank()) {
//            return Result.failure(Exception("El número de empleado es obligatorio"))
//        }

        if (password.isBlank()) {
            return Result.failure(Exception("La contraseña es obligatoria"))
        }

        if (confirmPassword.isBlank()) {
            return Result.failure(Exception("Debes confirmar tu contraseña"))
        }

        // Validación Formato de email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("El formato del email es inválido"))
        }

        // Validación Contraseñas coinciden
        if (password != confirmPassword) {
            return Result.failure(Exception("Las contraseñas no coinciden"))
        }

        // Validación Longitud mínima de contraseña
        if (password.length < 6) {
            return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
        }

        // Validación Número de empleado válido
//        if (noEmpleado.length < 3) {
//            return Result.failure(Exception("El número de empleado debe tener al menos 3 dígitos"))
//        }

//        // Validación Número de empleado solo contiene dígitos
//        if (!noEmpleado.all { it.isDigit() }) {
//            return Result.failure(Exception("El número de empleado solo debe contener números"))
//        }

        // Todas las validaciones pasaron, delegar al repository
        return try {
            authRepository.register(
                nombre = nombre,
                apellidoPaterno = apellidoPaterno,
                apellidoMaterno = apellidoMaterno,
                email = email,
                password = password
            )
        } catch (e: Exception) {
            Result.failure(Exception("Error al registrar: ${e.message}"))
        }
    }
}
package com.example.canchibol.data.repository

import android.content.Context
import com.example.canchibol.data.network.ApiService
import com.example.canchibol.domain.entity.UserEntity
import com.example.canchibol.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio de autenticación
 * Esta clase maneja la lógica de acceso a datos (API, Base de datos, etc.)
 */
class AuthRepositoryImpl(private val context: Context) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> {
        return try {
            // TODO: Implementar login cuando lo necesites
            // Por ahora devolvemos un error o un usuario temporal
            Result.failure(Exception("Login no implementado aún"))

            /* Para implementar después:
            val success = ApiService.loginUser(context, email, password)
            if (success) {
                Result.success(UserEntity(
                    id = "1",
                    email = email,
                    nombre = "Usuario Logeado",
                    token = "token_temp"
                ))
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
            }
            */
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String
    ): Result<UserEntity> {
        return try {
            // Usar withContext para ejecutar en el hilo de IO
            withContext(Dispatchers.IO) {
                val success = ApiService.registerUser(
                    context = context,
                    nombre = nombre,
                    apellidoPaterno = apellidoPaterno,
                    apellidoMaterno = apellidoMaterno,
                    correo = email,
                    contrasenia = password
                )

                if (success) {
                    // Crear un UserEntity con los datos registrados
                    Result.success(
                        UserEntity(
                            id = "temp_id", // Tu API no devuelve ID, podrías usar el email como referencia
                            email = email,
                            nombre = "$nombre $apellidoPaterno $apellidoMaterno",
                            token = "token_generado" // Podrías generar un token temporal
                        )
                    )
                } else {
                    Result.failure(Exception("Error al registrar el usuario en el servidor"))
                }
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            // Limpiar cualquier dato de sesión local
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        // Verificar si hay una sesión activa (por implementar)
        return false
    }
}
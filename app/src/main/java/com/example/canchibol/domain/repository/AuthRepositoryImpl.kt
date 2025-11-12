package com.example.canchibol.data.repository

import android.content.Context
import com.example.canchibol.data.network.ApiService
import com.example.canchibol.domain.entity.UserEntity
import com.example.canchibol.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(private val context: Context) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> {
        return try {
            withContext(Dispatchers.IO) {
                val result = ApiService.loginUser(context, email, password)

                if (result.isSuccess) {
                    val loginResponse = result.getOrNull()
                    if (loginResponse?.success == true && loginResponse.user != null) {
                        val user = loginResponse.user
                        Result.success(
                            UserEntity(
                                id = user.numEmpleado,
                                email = user.correo,
                                nombre = "${user.nombre} ${user.apellidoPaterno} ${user.apellidoMaterno}",

                                token = "token_${user.numEmpleado}" //Genera tokern real
                            )
                        )
                    } else {
                        Result.failure(Exception(loginResponse?.message ?:
                        "Error desconocido"))
                    }
                } else {
                    Result.failure(result.exceptionOrNull() ?: Exception
                        ("Error de login"))
                }
            }
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
                    Result.success(
                        UserEntity(
                            id = "temp_id",
                            email = email,
                            nombre = "$nombre $apellidoPaterno $apellidoMaterno",
                            token = "token_generado"
                        )
                    )
                } else {
                    Result.failure(Exception
                        ("Error al registrar el usuario en el servidor"))
                }
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            // Aquí luego podrías limpiar SharedPreferences o DataStore
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        // Por implementar - verificar si hay usuario logeado
        return false
    }
}
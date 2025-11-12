// En data/network/ApiService.kt
package com.example.canchibol.data.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import org.json.JSONObject

object ApiService {

    // Cambia esta IP por tu IP local
    private const val BASE_URL = "http://192.168.137.51/android_database_canchibol/"

    suspend fun registerUser(
        context: Context,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        correo: String,
        contrasenia: String
    ): Boolean = suspendCancellableCoroutine { continuation ->

        val url = "${BASE_URL}insertar.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                // Éxito - verifica la respuesta de tu PHP
                if (response.contains("exitoso") || response.contains
                        ("El usuario se inserto de forma exitosa")) {
                    continuation.resume(true)
                } else {
                    continuation.resume(false)
                }
            },
            { error ->
                // Error de Volley
                continuation.resumeWithException(Exception("Error de conexión: " +
                        "${error.message}"))
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["nombre"] = nombre
                params["apellidoPaterno"] = apellidoPaterno
                params["apellidoMaterno"] = apellidoMaterno
                params["correo"] = correo
                params["contrasenia"] = contrasenia
                return params
            }

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/x-www-form-urlencoded"
                return headers
            }
        }

        // Agregar a la cola de Volley
        Volley.newRequestQueue(context).add(stringRequest)

        // Cancellation callback
        continuation.invokeOnCancellation {
            // Opcional: cancelar la request si el coroutine se cancela
            stringRequest.cancel()
        }
    }
    suspend fun loginUser(
        context: Context,
        correo: String,
        contrasenia: String
    ): Result<LoginResponse> = suspendCancellableCoroutine { continuation ->

        val url = "${BASE_URL}login.php"

        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                println("RESPUESTA DEL SERVIDOR: '$response'")

                try {
                    val cleanResponse = if (response.contains('{')) {
                        response.substring(response.indexOf('{'))
                    } else {
                        response
                    }.trim()

                    println("RESPUESTA LIMPIA: '$cleanResponse'")

                    val jsonObject = JSONObject(cleanResponse)
                    val success = jsonObject.getBoolean("success")
                    val message = jsonObject.getString("message")

                    if (success) {
                        val userObject = jsonObject.getJSONObject("user")

                        val user = UserResponse(
                            numEmpleado = userObject.getString("numEmpleado"),
                            nombre = userObject.getString("nombre"),
                            apellidoPaterno = userObject.getString("apellidoPaterno"),
                            apellidoMaterno = userObject.getString("apellidoMaterno"),
                            correo = userObject.getString("correo")
                        )
                        println("LOGIN EXITOSO - Usuario: ${user.nombre}")
                        continuation.resume(Result.success(LoginResponse(success, message, user)))
                    } else {
                        println("LOGIN FALLIDO: $message")
                        continuation.resume(Result.failure(Exception(message)))
                    }
                } catch (e: Exception) {
                    println("ERROR PARSEANDO JSON: ${e.message}")
                    println("RESPUESTA COMPLETA: '$response'")
                    continuation.resume(Result.failure(Exception("Error en formato de respuesta: ${e.message}")))
                }
            },
            { error ->
                println("ERROR DE CONEXIÓN: ${error.message}")
                continuation.resume(Result.failure(Exception("Error de conexión: ${error.message}")))
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["correo"] = correo
                params["contrasenia"] = contrasenia
                return params
            }
        }

        Volley.newRequestQueue(context).add(stringRequest)
    }
}

// Modelos de respuesta para el login
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val user: UserResponse?
)

data class UserResponse(
    val numEmpleado: String,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val correo: String
)
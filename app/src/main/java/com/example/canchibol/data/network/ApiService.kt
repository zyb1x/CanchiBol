package com.example.canchibol.data.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.canchibol.data.network.dto.PartidoResponse
import com.example.canchibol.data.session.SessionManager
import com.google.gson.GsonBuilder
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import java.net.URLEncoder
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object ApiService {

    // Cambia esta ip por la ip de tu máquina
    private const val BASE_URL = "https://canchibol.alwaysdata.net/android_database_canchibol/"

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
                if (response.contains("exitoso") || response.contains("El usuario se inserto de forma exitosa")) {
                    continuation.resume(true)
                } else {
                    continuation.resume(false)
                }
            },
            { error ->
                continuation.resumeWithException(Exception("Error de conexión: ${error.message}"))
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

        Volley.newRequestQueue(context).add(stringRequest)

        continuation.invokeOnCancellation {
            stringRequest.cancel()
        }
    }

    suspend fun loginUser(
        context: Context,
        correo: String,
        contrasenia: String
    ): Result<LoginResponse> = suspendCancellableCoroutine { continuation ->

        val url = "${BASE_URL}login.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                try {
                    val cleanResponse = response.substringAfter('{').trim()
                    val jsonObject = JSONObject("{$cleanResponse")
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
                        // Guardar el usuario en la sesión
                        SessionManager.currentUser = user
                        continuation.resume(Result.success(LoginResponse(success, message, user)))
                    } else {
                        continuation.resume(Result.failure(Exception(message)))
                    }
                } catch (e: Exception) {
                    continuation.resume(Result.failure(Exception("Error en formato de respuesta: ${e.message}")))
                }
            },
            { error ->
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

    fun getPartidosPorFecha(fecha: String): Result<PartidoResponse> {
        return try {
            val encodedFecha = URLEncoder.encode(fecha, "UTF-8")
            val url = "$BASE_URL/get_partidos.php?fecha=$encodedFecha"
            val request = java.net.URL(url).openConnection() as java.net.HttpURLConnection
            request.requestMethod = "GET"
            request.connectTimeout = 10000
            request.readTimeout = 10000
            request.setRequestProperty("Accept-Charset", "UTF-8")
            request.setRequestProperty("Content-Type", "application/json; charset=utf-8")

            val responseCode = request.responseCode
            val responseBody = if (responseCode == 200) {
                request.inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
            } else {
                request.errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() } ?: "No error body"
            }

            if (responseCode == 200) {
                val cleanedResponse = responseBody.substringAfter('{').trim()
                val gson = GsonBuilder().setLenient().create()
                val partidoResponse = gson.fromJson("{$cleanedResponse", PartidoResponse::class.java)
                Result.success(partidoResponse)
            } else {
                Result.failure(Exception("Error HTTP $responseCode: $responseBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getProximosPartidos(): Result<PartidoResponse> {
        return try {
            val url = "$BASE_URL/get_proximos_partidos.php"
            val request = java.net.URL(url).openConnection() as java.net.HttpURLConnection
            request.requestMethod = "GET"
            request.connectTimeout = 10000
            request.readTimeout = 10000
            request.setRequestProperty("Accept-Charset", "UTF-8")
            request.setRequestProperty("Content-Type", "application/json; charset=utf-8")

            val responseCode = request.responseCode
            val responseBody = if (responseCode == 200) {
                request.inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
            } else {
                request.errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() } ?: "No error body"
            }

            if (responseCode == 200) {
                val cleanedResponse = responseBody.substringAfter('{').trim()
                val gson = GsonBuilder().setLenient().create()
                val partidoResponse = gson.fromJson("{$cleanedResponse", PartidoResponse::class.java)
                Result.success(partidoResponse)
            } else {
                Result.failure(Exception("Error HTTP $responseCode: $responseBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

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
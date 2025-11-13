// En data/network/ApiService.kt
package com.example.canchibol.data.network

import android.content.Context
import android.util.JsonReader
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.canchibol.data.network.dto.PartidoDto
import com.example.canchibol.data.network.dto.PartidoResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.StringReader
import java.net.URLEncoder


object ApiService {

    // Cambia esta ip por la ip local
    private const val BASE_URL = "http://192.168.137.116/android_database_canchibol/"

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

    suspend fun getPartidosPorFecha(context: Context, fecha: String): Result<PartidoResponse> {
        return try {
            // CODIFICAR LA FECHA
            val encodedFecha = URLEncoder.encode(fecha, "UTF-8")
            val url = "$BASE_URL/get_partidos.php?fecha=$encodedFecha"

            println("DEBUG: URL: $url")

            val request = java.net.URL(url).openConnection() as java.net.HttpURLConnection
            request.requestMethod = "GET"
            request.connectTimeout = 10000
            request.readTimeout = 10000


            request.setRequestProperty("Accept-Charset", "UTF-8")
            request.setRequestProperty("Content-Type", "application/json; charset=utf-8")

            val responseCode = request.responseCode
            println("DEBUG: Response Code: $responseCode")

            val responseBody = if (responseCode == 200) {

                request.inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
            } else {
                request.errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() } ?: "No error body"
            }

            println("DEBUG: Raw Response: '$responseBody'")

            if (responseCode == 200) {
                val cleanedResponse = responseBody.trim()

                try {

                    val gson = GsonBuilder()
                        .setLenient()
                        .create()

                    val partidoResponse = gson.fromJson(cleanedResponse, PartidoResponse::class.java)
                    Result.success(partidoResponse)
                } catch (e: Exception) {
                    println("DEBUG: Gson Error: ${e.message}")
                    Result.failure(Exception("Error parsing JSON: ${e.message}"))
                }
            } else {
                Result.failure(Exception("Error HTTP $responseCode: $responseBody"))
            }
        } catch (e: Exception) {
            println("DEBUG: Network Exception: ${e.message}")
            Result.failure(e)
        }
    }


    private fun parsePartidoResponseManual(json: String): PartidoResponse {
        return try {
            // Buscar los campos básicos en el JSON
            val success = """"success":\s*true""".toRegex().containsMatchIn(json)
            val partidos = mutableListOf<PartidoDto>()

            // Buscar array de partidos
            val partidoMatches = """\{"titular":"[^"]*","equipo1":"[^"]*","equipo2":"[^"]*","fecha":"[^"]*","hora":"[^"]*","cancha":"[^"]*"\}""".toRegex().findAll(json)

            partidoMatches.forEach { match ->
                val partidoJson = match.value
                val titular = """"titular":"([^"]*)"""".toRegex().find(partidoJson)?.groupValues?.get(1) ?: ""
                val equipo1 = """"equipo1":"([^"]*)"""".toRegex().find(partidoJson)?.groupValues?.get(1) ?: ""
                val equipo2 = """"equipo2":"([^"]*)"""".toRegex().find(partidoJson)?.groupValues?.get(1) ?: ""
                val fecha = """"fecha":"([^"]*)"""".toRegex().find(partidoJson)?.groupValues?.get(1) ?: ""
                val hora = """"hora":"([^"]*)"""".toRegex().find(partidoJson)?.groupValues?.get(1) ?: ""
                val cancha = """"cancha":"([^"]*)"""".toRegex().find(partidoJson)?.groupValues?.get(1) ?: ""

                partidos.add(PartidoDto(titular, equipo1, equipo2, fecha, hora, cancha))
            }

            PartidoResponse(success = success, partidos = partidos, message = null)
        } catch (e: Exception) {
            PartidoResponse(success = false, partidos = emptyList(), message = "Error parsing manually")
        }
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
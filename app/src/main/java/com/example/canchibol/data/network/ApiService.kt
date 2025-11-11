// En data/network/ApiService.kt
package com.example.canchibol.data.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object ApiService {

    // Cambia esta IP por tu IP local
    private const val BASE_URL = "http://172.16.100.73/android_database_canchibol/"

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
                if (response.contains("exitoso") || response.contains("El usuario se inserto de forma exitosa")) {
                    continuation.resume(true)
                } else {
                    continuation.resume(false)
                }
            },
            { error ->
                // Error de Volley
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

        // Agregar a la cola de Volley
        Volley.newRequestQueue(context).add(stringRequest)

        // Cancellation callback
        continuation.invokeOnCancellation {
            // Opcional: cancelar la request si el coroutine se cancela
            stringRequest.cancel()
        }
    }
}
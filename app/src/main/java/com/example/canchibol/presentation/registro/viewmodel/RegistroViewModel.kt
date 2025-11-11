package com.example.canchibol.presentation.registro.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.canchibol.domain.entity.UserEntity
import com.example.canchibol.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch


class RegistroViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    // Estados de los campos de texto
    private val _nombre = mutableStateOf("")
    val nombre: State<String> = _nombre

    private val _apellidoPaterno = mutableStateOf("")
    val apellidoPaterno: State<String> = _apellidoPaterno

    private val _apellidoMaterno = mutableStateOf("")
    val apellidoMaterno: State<String> = _apellidoMaterno

    private val _noEmpleado = mutableStateOf("")
    val noEmpleado: State<String> = _noEmpleado

    private val _correo = mutableStateOf("")
    val correo: State<String> = _correo

    private val _contrasenia = mutableStateOf("")
    val contrasenia: State<String> = _contrasenia

    private val _confirmarContrasenia = mutableStateOf("")
    val confirmarContrasenia: State<String> = _confirmarContrasenia

    // Estados de UI
    private val _mostrarPassword = mutableStateOf(false)
    val mostrarPassword: State<Boolean> = _mostrarPassword

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _mensajeError = mutableStateOf<String?>(null)
    val mensajeError: State<String?> = _mensajeError


    private val _registroExitoso = mutableStateOf<UserEntity?>(null)
    val registroExitoso: State<UserEntity?> = _registroExitoso

    // Funciones para actualizar los campos
    fun onNombreChange(value: String) {
        _nombre.value = value
        _mensajeError.value = null // Limpiar error al escribir
    }

    fun onApellidoPaternoChange(value: String) {
        _apellidoPaterno.value = value
        _mensajeError.value = null
    }

    fun onApellidoMaternoChange(value: String) {
        _apellidoMaterno.value = value
        _mensajeError.value = null
    }

    fun onNoEmpleadoChange(value: String) {
        _noEmpleado.value = value
        _mensajeError.value = null
    }

    fun onCorreoChange(value: String) {
        _correo.value = value
        _mensajeError.value = null
    }

    fun onContraseniaChange(value: String) {
        _contrasenia.value = value
        _mensajeError.value = null
    }

    fun onConfirmarContraseniaChange(value: String) {
        _confirmarContrasenia.value = value
        _mensajeError.value = null
    }

    fun alternarMostrarPassword() {
        _mostrarPassword.value = !_mostrarPassword.value
    }


    fun validarRegistro() {
        viewModelScope.launch {
            _isLoading.value = true
            _mensajeError.value = null

            // Construir el nombre completo
            val nombreCompleto = buildString {
                append(_nombre.value.trim())
                if (_apellidoPaterno.value.isNotBlank()) {
                    append(" ${_apellidoPaterno.value.trim()}")
                }
                if (_apellidoMaterno.value.isNotBlank()) {
                    append(" ${_apellidoMaterno.value.trim()}")
                }
            }


            val result = registerUseCase(
                nombre = nombre.value.trim(),
                apellidoPaterno = _apellidoPaterno.value.trim(),
                apellidoMaterno = _apellidoMaterno.value.trim(),
                email = _correo.value.trim(),
                password = _contrasenia.value,
                confirmPassword = _confirmarContrasenia.value,
                noEmpleado = _noEmpleado.value.trim()
            )

            // Manejar el resultado
            result.fold(
                onSuccess = { user ->
                    _registroExitoso.value = user
                    _mensajeError.value = null
                    // Aquí podrías mostrar un Toast o mensaje de éxito
                },
                onFailure = { exception ->
                    _mensajeError.value = exception.message ?: "Error desconocido"
                    _registroExitoso.value = null
                }
            )

            _isLoading.value = false
        }
    }


    fun resetRegistro() {
        _registroExitoso.value = null
        _mensajeError.value = null
    }
}
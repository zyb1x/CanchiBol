package com.example.canchibol.proyecto.registro.layouts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class RegistroViewModel : ViewModel() {

    private val _nombre = mutableStateOf("")
    val nombre: State<String> = _nombre
    private val _noEmpleado = mutableStateOf("")
    val noEmpleado: State<String> = _noEmpleado
    private val _correo = mutableStateOf("")
    val correo: State<String> = _correo
    private val _contrasenia = mutableStateOf("")
    val contrasenia: State<String> = _contrasenia
    private val _confirmarContrasenia = mutableStateOf("")
    val confirmarContrasenia: State<String> = _confirmarContrasenia
    private val _mostrarPassword = mutableStateOf(false)
    val mostrarPassword: State<Boolean> = _mostrarPassword
    private val _registroExitoso = mutableStateOf(false)
    val registroExitoso: State<Boolean> = _registroExitoso
    private val _mensajeError = mutableStateOf<String?>(null)

    val mensajeError: State<String?> = _mensajeError

    fun onNombreChange(value: String) { _nombre.value = value }

    fun onNoEmpleadoChange(value: String) { _noEmpleado.value = value }

    fun onCorreoChange(value: String) { _correo.value = value }

    fun onContraseniaChange(value: String) { _contrasenia.value = value }

    fun onConfirmarContraseniaChange(value: String) { _confirmarContrasenia.value = value }

    fun alternarMostrarPassword() { _mostrarPassword.value = !_mostrarPassword.value }

    fun validarRegistro() {
        if (_nombre.value.isBlank() ||
            _noEmpleado.value.isBlank() ||
            _correo.value.isBlank() ||
            _contrasenia.value.isBlank() ||
            _confirmarContrasenia.value.isBlank()
        ) {
            _mensajeError.value = "Completa todos los campos"
        } else if (_contrasenia.value != _confirmarContrasenia.value) {
            _mensajeError.value = "Contraseñas no coincide"
        } else {
            _mensajeError.value = null
            _registroExitoso.value = true
        }
    }

    fun resetRegistro() {
        _registroExitoso.value = false
        _mensajeError.value = null
    }
}

package com.example.canchibol.proyecto.iniciodesesion.layouts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class InicioDeSesionViewModel : ViewModel() {

    private val _usuario = mutableStateOf("")
    val usuario: State<String> = _usuario
    private val _contrasenia = mutableStateOf("")
    val contrasenia: State<String> = _contrasenia
    private val _mostrarPassword = mutableStateOf(false)
    val mostrarPassword: State<Boolean> = _mostrarPassword
    private val _loginExitoso = mutableStateOf(false)
    val loginExitoso: State<Boolean> = _loginExitoso
    private val _errorMensaje = mutableStateOf<String?>(null)
    val errorMensaje: State<String?> = _errorMensaje

    fun onUsuarioChange(value: String) {
        _usuario.value = value
    }

    fun onContraseniaChange(value: String) {
        _contrasenia.value = value
    }

    fun alternarMostrarPassword() {
        _mostrarPassword.value = !_mostrarPassword.value
    }
    fun validarLogin() {
        if (_usuario.value.isBlank() || _contrasenia.value.isBlank()) {
            _errorMensaje.value = "Completa todos los campos"
        } else if (_usuario.value == "chiwised@gmail.com" && _contrasenia.value == "12345") {
            _loginExitoso.value = true
            _errorMensaje.value = null
        } else {
            _errorMensaje.value = "Credenciales incorrectas"
        }
    }
    fun resetLogin() {
        _loginExitoso.value = false
        _errorMensaje.value = null
    }
}

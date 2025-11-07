package com.example.canchibol.presentation.iniciodesesion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.canchibol.domain.entity.UserEntity
import com.example.canchibol.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch


class InicioDeSesionViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // Estados de UI
    private val _usuario = mutableStateOf("")
    val usuario: State<String> = _usuario

    private val _contrasenia = mutableStateOf("")
    val contrasenia: State<String> = _contrasenia

    private val _mostrarPassword = mutableStateOf(false)
    val mostrarPassword: State<Boolean> = _mostrarPassword

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMensaje = mutableStateOf<String?>(null)
    val errorMensaje: State<String?> = _errorMensaje

    private val _loginExitoso = mutableStateOf<UserEntity?>(null)
    val loginExitoso: State<UserEntity?> = _loginExitoso

    // Funciones de actualización de estado
    fun onUsuarioChange(value: String) {
        _usuario.value = value
        _errorMensaje.value = null // Limpiar error al escribir
    }

    fun onContraseniaChange(value: String) {
        _contrasenia.value = value
        _errorMensaje.value = null // Limpiar error al escribir
    }

    fun alternarMostrarPassword() {
        _mostrarPassword.value = !_mostrarPassword.value
    }


    fun validarLogin() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMensaje.value = null

            // Llamar al UseCase - él tiene toda la lógica
            val result = loginUseCase(
                email = _usuario.value.trim(),
                password = _contrasenia.value
            )

            result.fold(
                onSuccess = { user ->
                    _loginExitoso.value = user
                    _errorMensaje.value = null
                    // Aquí podrías navegar a otra pantalla
                },
                onFailure = { exception ->
                    _errorMensaje.value = exception.message ?: "Error desconocido"
                    _loginExitoso.value = null
                }
            )

            _isLoading.value = false
        }
    }


    fun resetLogin() {
        _loginExitoso.value = null
        _errorMensaje.value = null
    }
}
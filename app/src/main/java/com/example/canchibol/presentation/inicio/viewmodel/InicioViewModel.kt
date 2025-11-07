package com.example.canchibol.presentation.inicio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class InicioViewModel : ViewModel() {
    private val _navegarIniciarSesion = mutableStateOf(false)
    val navegarIniciarSesion: State<Boolean> = _navegarIniciarSesion
    private val _navegarRegistro = mutableStateOf(false)
    val navegarRegistro: State<Boolean> = _navegarRegistro
    fun irAIniciarSesion() {
        _navegarIniciarSesion.value = true
    }
    fun irARegistro() {
        _navegarRegistro.value = true
    }
    fun resetNavegacion() {
        _navegarIniciarSesion.value = false
        _navegarRegistro.value = false
    }
}

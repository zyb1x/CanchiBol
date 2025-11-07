package com.example.canchibol.proyecto.menudinicio.layouts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class MenuInicioViewModel : ViewModel() {
    private val _opcionSeleccionada = mutableStateOf<String?>(null)
    val opcionSeleccionada: State<String?> = _opcionSeleccionada

    fun seleccionarOpcion(opcion: String) {
        _opcionSeleccionada.value = opcion
    }

}

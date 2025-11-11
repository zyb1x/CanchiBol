package com.example.canchibol.presentation.registro.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canchibol.data.repository.AuthRepositoryImpl
import com.example.canchibol.domain.usecase.RegisterUseCase

class RegistroViewModelFactory(
    private val context: Context  // ← Agrega el contexto como parámetro
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {

            val repository = AuthRepositoryImpl(context)
            val registerUseCase = RegisterUseCase(repository)

            return RegistroViewModel(registerUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
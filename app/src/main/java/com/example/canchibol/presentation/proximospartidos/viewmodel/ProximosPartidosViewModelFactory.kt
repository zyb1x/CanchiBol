package com.example.canchibol.presentation.proximospartidos.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canchibol.data.repository.PartidoRepositoryImpl
import com.example.canchibol.domain.usecase.GetProximosPartidosUseCase

class ProximosPartidosViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProximosPartidosViewModel::class.java)) {
            val repository = PartidoRepositoryImpl(context)
            val useCase = GetProximosPartidosUseCase(repository)
            @Suppress("UNCHECKED_CAST")
            return ProximosPartidosViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
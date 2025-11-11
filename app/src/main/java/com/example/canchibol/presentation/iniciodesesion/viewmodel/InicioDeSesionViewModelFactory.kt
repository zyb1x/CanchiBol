package com.example.canchibol.presentation.iniciodesesion.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canchibol.data.repository.AuthRepositoryImpl
import com.example.canchibol.domain.usecase.LoginUseCase

class InicioDeSesionViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InicioDeSesionViewModel::class.java)) {

            val repository = AuthRepositoryImpl(context)
            val loginUseCase = LoginUseCase(repository)

            return InicioDeSesionViewModel(loginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
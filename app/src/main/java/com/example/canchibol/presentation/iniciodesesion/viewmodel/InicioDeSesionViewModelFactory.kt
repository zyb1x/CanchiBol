package com.example.canchibol.presentation.iniciodesesion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canchibol.data.repository.AuthRepositoryImpl
import com.example.canchibol.domain.usecase.LoginUseCase


class InicioDeSesionViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InicioDeSesionViewModel::class.java)) {
            // Crear las dependencias manualmente
            val repository = AuthRepositoryImpl()
            val loginUseCase = LoginUseCase(repository)

            return InicioDeSesionViewModel(loginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/*
 * ALTERNATIVA CON HILT :
 *
 * @HiltViewModel
 * class InicioDeSesionViewModel @Inject constructor(
 *     private val loginUseCase: LoginUseCase
 * ) : ViewModel() {
 *     // ... resto del código
 * }
 *
 * Y en tu Composable:
 * @Composable
 * fun LayoutIniciarSesion(
 *     viewModel: InicioDeSesionViewModel = hiltViewModel()
 * ) { ... }
 */
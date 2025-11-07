package com.example.canchibol.presentation.registro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canchibol.data.repository.AuthRepositoryImpl
import com.example.canchibol.domain.usecase.RegisterUseCase

/**
 * Factory para crear el RegistroViewModel con sus dependencias
 *
 * Este Factory:
 * 1. Crea el Repository (implementación de datos)
 * 2. Crea el UseCase (lógica de negocio)
 * 3. Inyecta el UseCase en el ViewModel
 *
 * En un proyecto real con Hilt, esto NO sería necesario
 */
class RegistroViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            // Crear las dependencias en el orden correcto:
            // Repository -> UseCase -> ViewModel
            val repository = AuthRepositoryImpl()
            val registerUseCase = RegisterUseCase(repository)

            return RegistroViewModel(registerUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

/*
 * ============================================
 * ALTERNATIVA CON HILT (Recomendado)
 * ============================================
 *
 * Con Hilt, NO necesitas este Factory. Simplemente:
 *
 * 1. Anotaciones en el ViewModel:
 *
 * @HiltViewModel
 * class RegistroViewModel @Inject constructor(
 *     private val registerUseCase: RegisterUseCase
 * ) : ViewModel() { ... }
 *
 *
 * 2. Módulo de Hilt para proveer dependencias:
 *
 * @Module
 * @InstallIn(SingletonComponent::class)
 * object AppModule {
 *
 *     @Provides
 *     @Singleton
 *     fun provideAuthRepository(): AuthRepository {
 *         return AuthRepositoryImpl()
 *     }
 *
 *     @Provides
 *     fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
 *         return RegisterUseCase(repository)
 *     }
 * }
 *
 *
 * 3. En tu Composable:
 *
 * @Composable
 * fun LayoutRegistro(
 *     viewModel: RegistroViewModel = hiltViewModel()
 * ) { ... }
 *
 * ============================================
 */
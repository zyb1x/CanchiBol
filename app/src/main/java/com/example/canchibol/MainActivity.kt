package com.example.canchibol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.canchibol.presentation.inicio.ui.LayoutInicio
import com.example.canchibol.presentation.iniciodesesion.ui.LayoutIniciarSesion
import com.example.canchibol.presentation.registro.ui.LayoutRegistro
import com.example.canchibol.proyecto.menudinicio.layouts.ui.LayoutMenuInicio
import com.example.canchibol.ui.theme.CanchiBolTheme

/**
 * MainActivity - Actividad principal de la aplicación
 *
 * VERSIÓN FINAL con Clean Architecture + Navigation Compose
 *
 * ✅ Usa Navigation Compose para manejar la navegación
 * ✅ Los ViewModels se crean automáticamente en cada Screen con sus Factories
 * ✅ Maneja correctamente el back stack
 * ✅ Las pantallas usan callbacks en lugar de observar estados del ViewModel
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanchiBolTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Navegación principal de la aplicación
 *
 * Define todas las rutas y las transiciones entre pantallas
 */
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio", // Pantalla inicial
        modifier = modifier
    ) {
        // ========================================
        // PANTALLA DE INICIO/BIENVENIDA
        // ========================================
        composable("inicio") {
            LayoutInicio(
                modifier = Modifier.fillMaxSize(),
                // ✅ Callback: navegar a Login
                onNavigateToLogin = {
                    navController.navigate("login") {
                        // Opcional: eliminar "inicio" del back stack
                        // para que el botón atrás no regrese aquí
                        popUpTo("inicio") { inclusive = true }
                    }
                },
                // ✅ Callback: navegar a Registro
                onNavigateToRegister = {
                    navController.navigate("registro") {
                        // Opcional: eliminar "inicio" del back stack
                        popUpTo("inicio") { inclusive = true }
                    }
                }
            )
        }

        // ========================================
        // PANTALLA DE LOGIN
        // ========================================
        composable("login") {
            LayoutIniciarSesion(
                modifier = Modifier.fillMaxSize(),
                // ✅ El ViewModel se crea automáticamente con viewModel(factory = ...)
                // ✅ Callback: navegar a Registro
                onIrARegistro = {
                    navController.navigate("registro")
                },
                // ✅ Callback: navegar a Menú cuando login exitoso
                onLoginExitoso = {
                    navController.navigate("menu") {
                        // Limpiar el back stack para que el botón atrás
                        // NO regrese a login después de iniciar sesión
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // ========================================
        // PANTALLA DE REGISTRO
        // ========================================
        composable("registro") {
            LayoutRegistro(
                modifier = Modifier.fillMaxSize(),
                // ✅ El ViewModel se crea automáticamente con viewModel(factory = ...)
                // ✅ Callback: volver a Login
                onVolverLogin = {
                    // Usar popBackStack() para regresar a la pantalla anterior
                    navController.popBackStack()
                },
                // ✅ Callback: navegar a Login cuando registro exitoso
                onRegistroExitoso = {
                    navController.navigate("login") {
                        // Eliminar "registro" del back stack
                        popUpTo("registro") { inclusive = true }
                    }
                }
            )
        }

        // ========================================
        // PANTALLA DE MENÚ PRINCIPAL
        // ========================================
        composable("menu") {
            LayoutMenuInicio(
                modifier = Modifier.fillMaxSize(),
                // ✅ Callback: cerrar sesión y volver a Login
                onCerrarSesion = {
                    navController.navigate("login") {
                        // Limpiar TODO el back stack
                        // para que no pueda regresar al menú con el botón atrás
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
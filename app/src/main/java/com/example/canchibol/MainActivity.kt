package com.example.canchibol

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.canchibol.presentation.agendarpartido.ui.AgendarPartidoScreen
import com.example.canchibol.presentation.calendario.ui.CalendarioScreen
import com.example.canchibol.presentation.canchas.ui.CanchasScreen
import com.example.canchibol.presentation.inicio.ui.LayoutInicio
import com.example.canchibol.presentation.iniciodesesion.ui.LayoutIniciarSesion
import com.example.canchibol.presentation.registro.ui.LayoutRegistro
import com.example.canchibol.presentation.reporte.ui.ReporteScreen
import com.example.canchibol.proyecto.menudinicio.layouts.ui.LayoutMenuInicio
import com.example.canchibol.ui.theme.CanchiBolTheme


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanchiBolTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                   /* CalendarioScreen(onNavigateToAgendarPartido = { /* no hacer nada */ },
                        onNavigateToCanchas = { /* no hacer nada */ },
                        onNavigateToCalendario = { /* no hacer nada */ },
                        onNavigateToReporte = { /* no hacer nada */ },
                        onCerrarSesion = { /* no hacer nada */ })*/
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio", // Pantalla inicial
        modifier = modifier
    ) {

        // PANTALLA DE INICIO/BIENVENIDA

        composable("inicio") {
            LayoutInicio(
                modifier = Modifier.fillMaxSize(),

                onNavigateToLogin = {
                    navController.navigate("login") {
                        // Opcional: eliminar "inicio" del back stack
                        // para que el botón atrás no regrese aquí
                        popUpTo("inicio") { inclusive = true }
                    }
                },

                onNavigateToRegister = {
                    navController.navigate("registro") {
                        // Opcional: eliminar "inicio" del back stack
                        popUpTo("inicio") { inclusive = true }
                    }
                }
            )
        }


        // PANTALLA DE LOGIN

        composable("login") {
            LayoutIniciarSesion(
                modifier = Modifier.fillMaxSize(),

                onIrARegistro = {
                    navController.navigate("registro")
                },

                onLoginExitoso = {
                    navController.navigate("menu") {
                        // Limpiar el back stack para que el botón atrás
                        // NO regrese a login después de iniciar sesión
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }


        // PANTALLA DE REGISTRO

        composable("registro") {
            LayoutRegistro(
                modifier = Modifier.fillMaxSize(),

                onVolverLogin = {

                    navController.popBackStack()
                },

                onRegistroExitoso = {
                    navController.navigate("login") {
                        // Eliminar "registro" del back stack
                        popUpTo("registro") { inclusive = true }
                    }
                },

            )
        }


        // PANTALLA DE MENU PRINCIPAL

        composable("menu") {
            LayoutMenuInicio(
                modifier = Modifier.fillMaxSize(),

                onCerrarSesion = {
                    navController.navigate("login") {

                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToAgendarPartido = {
                    navController.navigate("agendar_partido")
                },
                onNavigateToCanchas = {
                    navController.navigate("canchas")
                },
                onNavigateToCalendario = {
                    navController.navigate("calendario")
                },
                onNavigateToReporte = {
                    navController.navigate("reporte")
                }
            )
        }
        // Pantalla de Agendar Partido
        composable("agendar_partido") {
            AgendarPartidoScreen(
                onNavigateToAgendarPartido = {

                    navController.navigate("agendar_partido") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCanchas = {
                    navController.navigate("canchas") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCalendario = {
                    navController.navigate("calendario") {
                        launchSingleTop = true
                    }
                },
                onNavigateToReporte = {
                    navController.navigate("reporte") {
                        launchSingleTop = true
                    }
                },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Canchas
        composable("canchas") {
            CanchasScreen(
                onNavigateToAgendarPartido = {
                    navController.navigate("agendar_partido") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCanchas = {
                    // Ya estamos en esta pantalla
                    navController.navigate("canchas") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCalendario = {
                    navController.navigate("calendario") {
                        launchSingleTop = true
                    }
                },
                onNavigateToReporte = {
                    navController.navigate("reporte") {
                        launchSingleTop = true
                    }
                },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Calendario
        composable("calendario") {
            CalendarioScreen(
                onNavigateToAgendarPartido = {
                    navController.navigate("agendar_partido") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCanchas = {
                    navController.navigate("canchas") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCalendario = {
                    navController.navigate("calendario") {
                        launchSingleTop = true
                    }
                },
                onNavigateToReporte = {
                    navController.navigate("reporte") {
                        launchSingleTop = true
                    }
                },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToInicio = {
                    navController.navigate("menu"){
                        launchSingleTop = true
                    }
                }
            )
        }

        // Pantalla de Reporte
        composable("reporte") {
            ReporteScreen(
                onNavigateToAgendarPartido = {
                    navController.navigate("agendar_partido") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCanchas = {
                    navController.navigate("canchas") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCalendario = {
                    navController.navigate("calendario") {
                        launchSingleTop = true
                    }
                },
                onNavigateToReporte = {
                    navController.navigate("reporte") {
                        launchSingleTop = true
                    }
                },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}


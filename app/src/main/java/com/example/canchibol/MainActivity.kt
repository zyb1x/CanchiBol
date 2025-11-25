package com.example.canchibol

import android.os.Build
import android.os.Bundle
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
import com.example.canchibol.presentation.calendario.ui.CalendarioScreen
import com.example.canchibol.presentation.inicio.ui.LayoutInicio
import com.example.canchibol.presentation.iniciodesesion.ui.LayoutIniciarSesion
import com.example.canchibol.presentation.perfil.ui.PerfilScreen
import com.example.canchibol.presentation.proximospartidos.ui.ProximosPartidosScreen
import com.example.canchibol.presentation.registro.ui.LayoutRegistro
import com.example.canchibol.presentation.menudeinicio.ui.LayoutMenuInicio
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
        startDestination = "inicio",
        modifier = modifier
    ) {

        composable("inicio") { backStackEntry ->
            LayoutInicio(
                modifier = Modifier.fillMaxSize(),
                onNavigateToLogin = { navController.navigate("login") { popUpTo("inicio") { inclusive = true } } },
                onNavigateToRegister = { navController.navigate("registro") { popUpTo("inicio") { inclusive = true } } }
            )
        }

        composable("login") { backStackEntry ->
            LayoutIniciarSesion(
                modifier = Modifier.fillMaxSize(),
                onIrARegistro = { navController.navigate("registro") },
                onLoginExitoso = { navController.navigate("menu") { popUpTo("login") { inclusive = true } } }
            )
        }

        composable("registro") { backStackEntry ->
            LayoutRegistro(
                modifier = Modifier.fillMaxSize(),
                onVolverLogin = { navController.navigateUp() },
                onRegistroExitoso = { navController.navigate("login") { popUpTo("registro") { inclusive = true } } },
            )
        }

        val onNavigateTo: (String) -> Unit = { route -> navController.navigate(route) { launchSingleTop = true } }
        val onLogout: () -> Unit = { navController.navigate("login") { popUpTo(0) { inclusive = true } } }

        composable("menu") { backStackEntry ->
            LayoutMenuInicio(
                onNavigateToInicio = { onNavigateTo("menu") },
                onNavigateToPerfil = { onNavigateTo("perfil") },
                onNavigateToProximosPartidos = { onNavigateTo("proximos_partidos") },
                onNavigateToCalendario = { onNavigateTo("calendario") },
                onCerrarSesion = onLogout
            )
        }

        composable("perfil") { backStackEntry ->
            PerfilScreen(
                onNavigateToInicio = { onNavigateTo("menu") },
                onNavigateToPerfil = { onNavigateTo("perfil") },
                onNavigateToProximosPartidos = { onNavigateTo("proximos_partidos") },
                onNavigateToCalendario = { onNavigateTo("calendario") },
                onCerrarSesion = onLogout
            )
        }

        composable("proximos_partidos") { backStackEntry ->
            ProximosPartidosScreen(
                onNavigateToInicio = { onNavigateTo("menu") },
                onNavigateToPerfil = { onNavigateTo("perfil") },
                onNavigateToProximosPartidos = { onNavigateTo("proximos_partidos") },
                onNavigateToCalendario = { onNavigateTo("calendario") },
                onCerrarSesion = onLogout
            )
        }

        composable("calendario") { backStackEntry ->
            CalendarioScreen(
                onNavigateToInicio = { onNavigateTo("menu") },
                onNavigateToPerfil = { onNavigateTo("perfil") },
                onNavigateToProximosPartidos = { onNavigateTo("proximos_partidos") },
                onNavigateToCalendario = { onNavigateTo("calendario") },
                onCerrarSesion = onLogout
            )
        }
    }
}
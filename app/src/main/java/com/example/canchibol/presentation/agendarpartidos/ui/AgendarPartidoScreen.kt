package com.example.canchibol.presentation.agendarpartido.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarPartidoScreen(
    onNavigateToInicio: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onNavigateToProximosPartidos: () -> Unit,
    onNavigateToAgendarPartido: () -> Unit,
    onNavigateToCanchas: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onNavigateToReporte: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "CanchiBol",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = DarkGreen
                    )
                    HorizontalDivider()

                    Text(
                        "Navegación",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = DarkGreen
                    )

                    NavigationDrawerItem(
                        label = { Text("Inicio") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToInicio() }
                    )

                    NavigationDrawerItem(
                        label = { Text("Próximos Partidos") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToProximosPartidos() }
                    )
                    
                    NavigationDrawerItem(
                        label = { Text("Agendar Partido") },
                        selected = true,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToAgendarPartido() },
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = LightGreen)
                    )
                    
                    NavigationDrawerItem(
                        label = { Text("Canchas") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToCanchas() }
                    )
                    
                    NavigationDrawerItem(
                        label = { Text("Calendario") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToCalendario() }
                    )

                    NavigationDrawerItem(
                        label = { Text("Reporte") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToReporte() }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(
                        "Sesión",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = DarkGreen
                    )

                    NavigationDrawerItem(
                        label = { Text("Perfil") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToPerfil() }
                    )

                    NavigationDrawerItem(
                        label = { Text("Cerrar Sesión") },
                        selected = false,
                        icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = "Cerrar sesión", tint = DarkGreen) },
                        onClick = { scope.launch { drawerState.close() }; onCerrarSesion() }
                    )

                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Agendar Partido", color = LightGreen) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú", tint = LightGreen)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MediumGreen)
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Contenido de Agendar Partido",
                    color = DarkGreen,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
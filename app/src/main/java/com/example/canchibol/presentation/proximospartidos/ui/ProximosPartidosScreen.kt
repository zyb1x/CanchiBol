package com.example.canchibol.presentation.proximospartidos.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.presentation.calendario.ui.components.PartidosList
import com.example.canchibol.presentation.proximospartidos.viewmodel.ProximosPartidosViewModel
import com.example.canchibol.presentation.proximospartidos.viewmodel.ProximosPartidosViewModelFactory
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProximosPartidosScreen(
    modifier: Modifier = Modifier,
    onNavigateToInicio: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onNavigateToProximosPartidos: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onCerrarSesion: () -> Unit,
    onNavigateToMapa: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: ProximosPartidosViewModel = viewModel(factory = ProximosPartidosViewModelFactory(context))
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState

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
                        selected = true,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToProximosPartidos() },
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = LightGreen)
                    )
                    
                    NavigationDrawerItem(
                        label = { Text("Calendario") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToCalendario() }
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

                    Spacer(Modifier.height(250.dp))

                    // Footer del drawer
                    Text(
                        "CanchiBol v1.0",
                        style = MaterialTheme.typography.bodySmall,
                        color = DarkGreen.copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
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
                    title = { Text("Próximos Partidos", color = LightGreen) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú", tint = LightGreen)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MediumGreen)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                PartidosList(
                    partidos = uiState.partidos,
                    isLoading = uiState.isLoading,
                    error = uiState.error,
                    modifier = Modifier.fillMaxSize() // Modificador añadido
                )
            }
        }
    }
}
package com.example.canchibol.proyecto.menudinicio.layouts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.proyecto.menudinicio.layouts.viewmodel.MenuInicioViewModel
import com.example.canchibol.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutMenuInicio(
    modifier: Modifier,
    viewModel: MenuInicioViewModel = viewModel(),
    onCerrarSesion: () -> Unit,
    onNavigateToAgendarPartido: () -> Unit,
    onNavigateToCanchas: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onNavigateToReporte: () -> Unit
) {
    val opcionSeleccionada by viewModel.opcionSeleccionada
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

                    // Opción: Inicio (esta pantalla)
                    NavigationDrawerItem(
                        label = { Text("Inicio") },
                        selected = true, // Esta pantalla está activa
                        onClick = {
                            scope.launch { drawerState.close() }
                            // Ya estamos en inicio, no hace nada
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = LightGreen,
                            unselectedContainerColor = MaterialTheme.colorScheme.surface
                        )
                    )

                    // Opción: Calendario
                    NavigationDrawerItem(
                        label = { Text("Calendario") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            onNavigateToCalendario()
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(
                        "Sesión",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = DarkGreen
                    )

                    // Opción: Cerrar Sesión
                    NavigationDrawerItem(
                        label = { Text("Cerrar Sesión") },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.AutoMirrored.Outlined.Help,
                                contentDescription = "Cerrar sesión",
                                tint = DarkGreen
                            )
                        },
                        onClick = {
                            scope.launch { drawerState.close() }
                            onCerrarSesion()
                        }
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
                    title = {
                        Text(
                            "Inicio",
                            color = LightGreen
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = LightGreen
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MediumGreen
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Solo mantener el botón de Calendario
                Surface(
                    modifier = Modifier
                        .height(130.dp)
                        .width(300.dp),
                    onClick = {
                        viewModel.seleccionarOpcion("Calendario")
                        onNavigateToCalendario()
                    },
                    shadowElevation = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = LightGreen,
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calenda_icon_dark),
                            contentDescription = "Icono calendario",
                            modifier = Modifier
                                .size(55.dp)
                                .padding(bottom = 10.dp)
                        )
                        Text(
                            text = "Calendario",
                            modifier = Modifier.padding(top = 90.dp),
                            color = DarkGreen,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
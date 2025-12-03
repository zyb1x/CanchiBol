package com.example.canchibol.presentation.menudeinicio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.presentation.menudeinicio.viewmodel.MenuInicioViewModel
import com.example.canchibol.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutMenuInicio(
    modifier: Modifier = Modifier,
    viewModel: MenuInicioViewModel = viewModel(),
    onNavigateToInicio: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onNavigateToProximosPartidos: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onCerrarSesion: () -> Unit,
    onNavigateToMapa: () -> Unit = {}
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
                        selected = true,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToInicio() },
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = LightGreen)
                    )

                    NavigationDrawerItem(
                        label = { Text("Próximos Partidos") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToProximosPartidos() }
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
                    title = { Text("Inicio", color = LightGreen) },
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .height(130.dp)
                        .width(300.dp),
                    onClick = { onNavigateToCalendario() },
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

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    modifier = Modifier
                        .height(130.dp)
                        .width(300.dp),
                    onClick = { onNavigateToProximosPartidos() },
                    shadowElevation = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = LightGreen,
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SportsSoccer,
                            contentDescription = "Icono Próximos Partidos",
                            modifier = Modifier
                                .size(55.dp)
                                .padding(bottom = 10.dp),
                            tint = DarkGreen
                        )
                        Text(
                            text = "Próximos Partidos",
                            modifier = Modifier.padding(top = 90.dp),
                            color = DarkGreen,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}
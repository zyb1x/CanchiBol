package com.example.canchibol.presentation.calendario.ui

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.presentation.calendario.ui.components.PartidosList
import com.example.canchibol.presentation.calendario.viewmodel.CalendarioViewModel
import com.example.canchibol.presentation.calendario.viewmodel.CalendarioViewModelFactory
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioScreen(
    onNavigateToInicio: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onNavigateToProximosPartidos: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: CalendarioViewModel = viewModel(factory = CalendarioViewModelFactory(context))
    val uiState by viewModel.uiState.collectAsState()

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
                        label = { Text("Calendario") },
                        selected = true,
                        onClick = { scope.launch { drawerState.close() }; onNavigateToCalendario() },
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = LightGreen)
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
                    title = { Text("Calendario", color = LightGreen) },
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = { viewModel.showDatePicker()  }
                ) {
                    Text("Seleccionar Fecha")
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (uiState.selectedDate != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Fecha seleccionada:",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            formatDate(uiState.selectedDate!!),
                            style = MaterialTheme.typography.headlineSmall,
                            color = DarkGreen,
                            fontWeight = FontWeight.Bold
                        )

                        PartidosList(
                            partidos = uiState.partidos,
                            isLoading = uiState.isLoading,
                            error = uiState.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }
                } else {
                    Text(
                        "No hay fecha seleccionada",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }

            if (uiState.showDatePicker) {
                DatePickerModal(
                    onDateSelected = { date ->
                        viewModel.onDateSelected(date)
                    },
                    onDismiss = {
                        viewModel.hideDatePicker()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("Seleccionar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun formatDate(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("es", "ES"))
    return formatter.format(date)
}

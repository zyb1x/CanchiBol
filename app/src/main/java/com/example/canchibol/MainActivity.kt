package com.example.canchibol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.canchibol.proyecto.inicio.layouts.ui.LayoutInicio
import com.example.canchibol.proyecto.iniciodesesion.layouts.ui.LayoutIniciarSesion
import com.example.canchibol.proyecto.registro.layouts.ui.LayoutRegistro
import com.example.canchibol.proyecto.menudinicio.layouts.ui.LayoutMenuInicio
import com.example.canchibol.proyecto.inicio.layouts.viewmodel.InicioViewModel
import com.example.canchibol.proyecto.iniciodesesion.layouts.viewmodel.InicioDeSesionViewModel
import com.example.canchibol.proyecto.registro.layouts.viewmodel.RegistroViewModel
import com.example.canchibol.proyecto.menudinicio.layouts.viewmodel.MenuInicioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var pantallaActual by remember { mutableStateOf("inicio") }

            val inicioViewModel = remember { InicioViewModel() }
            val loginViewModel = remember { InicioDeSesionViewModel() }
            val registroViewModel = remember { RegistroViewModel() }
            val menuViewModel = remember { MenuInicioViewModel() }
            when (pantallaActual) {

                "inicio" -> {
                    LayoutInicio(modifier = Modifier, viewModel = inicioViewModel)
                    if (inicioViewModel.navegarIniciarSesion.value) {
                        pantallaActual = "login"
                        inicioViewModel.resetNavegacion()
                    } else if (inicioViewModel.navegarRegistro.value) {
                        pantallaActual = "registro"
                        inicioViewModel.resetNavegacion()
                    }
                }

                "login" -> {
                    LayoutIniciarSesion(
                        modifier = Modifier,
                        viewModel = loginViewModel,
                        onIrARegistro = { pantallaActual = "registro" }
                    )

                    if (loginViewModel.loginExitoso.value) {
                        pantallaActual = "menu"
                        loginViewModel.resetLogin()
                    }
                }

                "registro" -> {
                    LayoutRegistro(
                        modifier = Modifier,
                        viewModel = registroViewModel,
                        onVolverLogin = { pantallaActual = "login" }
                    )

                    if (registroViewModel.registroExitoso.value) {
                        pantallaActual = "login"
                        registroViewModel.resetRegistro()
                    }
                }

                "menu" -> {
                        LayoutMenuInicio(
                            modifier = Modifier,
                            viewModel = menuViewModel,
                            onCerrarSesion = {
                                pantallaActual = "login"
                            }
                        )
                    }

                }

            }

        }
    }


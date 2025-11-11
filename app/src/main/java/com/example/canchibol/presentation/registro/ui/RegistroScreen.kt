package com.example.canchibol.presentation.registro.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.presentation.registro.viewmodel.RegistroViewModel
import com.example.canchibol.presentation.registro.viewmodel.RegistroViewModelFactory
import com.example.canchibol.ui.theme.*


@Composable
fun LayoutRegistro(
    modifier: Modifier = Modifier,
    viewModel: RegistroViewModel = viewModel(factory = RegistroViewModelFactory(LocalContext.current)),
    onVolverLogin: () -> Unit,
    onRegistroExitoso: () -> Unit
) {

    val context = LocalContext.current

    val nombre by viewModel.nombre
    val apellidoPaterno by viewModel.apellidoPaterno
    val apellidoMaterno by viewModel.apellidoMaterno
    val noEmpleado by viewModel.noEmpleado
    val correo by viewModel.correo
    val contrasenia by viewModel.contrasenia
    val confContra by viewModel.confirmarContrasenia
    val showPassword by viewModel.mostrarPassword
    val error by viewModel.mensajeError
    val isLoading by viewModel.isLoading
    val registroExitoso by viewModel.registroExitoso
    val currentError = error


    LaunchedEffect(registroExitoso) {
        if (registroExitoso != null) {
            onRegistroExitoso() // Navegar al login
            viewModel.resetRegistro() // Limpiar el estado
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (logo, textFieldNombre, textFieldNEmpleado, textFieldCorreo,
            textFieldContrasenia, textFieldConfContra, btnRegistrarse,
            btnIniciarSesion, errorText, textFieldApellidoPaterno,
            textFieldApellidoMaterno) = createRefs()

        // Logo de la aplicación
        Image(
            painter = painterResource(id = R.drawable.logo_canchas),
            contentDescription = "Logo Aplicacion",
            modifier = Modifier
                .size(90.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 5.dp)
                }
        )

        // TextField nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.onNombreChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldNombre) {
                    top.linkTo(logo.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Nombre(s)", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedLeadingIconColor = DarkGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // TextField apellido paterno
        OutlinedTextField(
            value = apellidoPaterno,
            onValueChange = { viewModel.onApellidoPaternoChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldApellidoPaterno) {
                    top.linkTo(textFieldNombre.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Apellido paterno", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedLeadingIconColor = DarkGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // TextField apellido materno
        OutlinedTextField(
            value = apellidoMaterno,
            onValueChange = { viewModel.onApellidoMaternoChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldApellidoMaterno) {
                    top.linkTo(textFieldApellidoPaterno.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Apellido materno", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedLeadingIconColor = DarkGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // TextField de número de Empleado
        OutlinedTextField(
            value = noEmpleado,
            onValueChange = { viewModel.onNoEmpleadoChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldNEmpleado) {
                    top.linkTo(textFieldApellidoMaterno.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Número de empleado", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedLeadingIconColor = DarkGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // TextField de Correo
        OutlinedTextField(
            value = correo,
            onValueChange = { viewModel.onCorreoChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldCorreo) {
                    top.linkTo(textFieldNEmpleado.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Correo", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedLeadingIconColor = DarkGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // TextField Contraseña
        OutlinedTextField(
            value = contrasenia,
            onValueChange = { viewModel.onContraseniaChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldContrasenia) {
                    top.linkTo(textFieldCorreo.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Contraseña", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = { viewModel.alternarMostrarPassword() }) {
                    Icon(
                        imageVector = if (showPassword) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = if (showPassword) {
                            "Ocultar contraseña"
                        } else {
                            "Mostrar contraseña"
                        }
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // TextField de Confirmar Contraseña
        OutlinedTextField(
            value = confContra,
            onValueChange = { viewModel.onConfirmarContraseniaChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldConfContra) {
                    top.linkTo(textFieldContrasenia.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text("Confirmar contraseña", fontSize = 14.sp, lineHeight = 14.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            enabled = !isLoading,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = { viewModel.alternarMostrarPassword() }) {
                    Icon(
                        imageVector = if (showPassword) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = if (showPassword) {
                            "Ocultar contraseña"
                        } else {
                            "Mostrar contraseña"
                        }
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = GrayGreen,
                focusedTextColor = MediumGreen,
                unfocusedTextColor = MediumGreen,
                cursorColor = MediumGreen,
                focusedPlaceholderColor = MediumGreen,
                unfocusedPlaceholderColor = DarkGreen,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // Botón de Registrarse
        Button(
            onClick = { viewModel.validarRegistro() },
            modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .constrainAs(btnRegistrarse) {
                    top.linkTo(textFieldConfContra.bottom, margin = 65.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            enabled = !isLoading,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )
        ) {
            if (isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = LightGreen,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Registrarse", fontSize = 15.sp)
            }
        }

        // Mostrar mensaje de error si existe
        if (!currentError.isNullOrEmpty()) {
            Text(
                text = currentError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(errorText) {
                    top.linkTo(btnRegistrarse.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }

        // Botón para volver al Login
        Button(
            onClick = onVolverLogin,
            modifier = Modifier
                .constrainAs(btnIniciarSesion) {
                    end.linkTo(logo.start, margin = 60.dp)
                    bottom.linkTo(logo.bottom, margin = 20.dp)
                },
            enabled = !isLoading,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = MediumGreen
            )
        ) {
            Text("<-", fontSize = 20.sp)
        }
    }
}
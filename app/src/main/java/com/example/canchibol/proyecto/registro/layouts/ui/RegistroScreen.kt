package com.example.canchibol.proyecto.registro.layouts.ui

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.proyecto.registro.layouts.viewmodel.RegistroViewModel
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.GrayGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen

@Composable
fun LayoutRegistro(
    modifier: Modifier,
    viewModel: RegistroViewModel = viewModel(),
    onVolverLogin: () -> Unit
) {

    val nombre by viewModel.nombre
    val noEmpleado by viewModel.noEmpleado
    val correo by viewModel.correo
    val contrasenia by viewModel.contrasenia
    val confContra by viewModel.confirmarContrasenia
    val showPassword by viewModel.mostrarPassword
    val error = viewModel.mensajeError.value

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (logo, textFieldNombre, textFieldNEmpleado, textFieldCorreo,
            textFieldContrasenia, textFieldConfContra, btnRegistrarse,
            btnIniciarSesion, errorText) = createRefs()

        // Logo de la aplicación
        Image(
            painter = painterResource(id = R.drawable.logo_canchas),
            contentDescription = "Logo Aplicacion",
            modifier = Modifier
                .size(90.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 20.dp)
                }
        )


        OutlinedTextField( //Textfield nombre
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
                Text(
                    "Nombre(s)",
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
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

        // TextField de numero de Empleado
        OutlinedTextField(
            value = noEmpleado,
            onValueChange = { viewModel.onNoEmpleadoChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldNEmpleado) {
                    top.linkTo(textFieldNombre.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text(
                    "Número de empleado",
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
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
                Text(
                    "Correo",
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
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


        OutlinedTextField( //Texfield Contraseña
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
                Text(
                    "Contraseña",
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(

                    onClick = { viewModel.alternarMostrarPassword() }
                ) {
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
                Text(
                    "Confirmar contraseña",
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = { viewModel.alternarMostrarPassword() }
                ) {
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
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )
        ) {
            Text("Registrarse", fontSize = 15.sp)
        }


        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(errorText) {
                    top.linkTo(btnRegistrarse.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }

        // Botón de Iniciar Sesión
        Button(
            onClick = { onVolverLogin() },  // Navega de regreso al login
            modifier = Modifier
                .width(158.dp)
                .height(45.dp)
                .constrainAs(btnIniciarSesion) {
                    top.linkTo(btnRegistrarse.bottom, margin = 95.dp)
                    start.linkTo(btnRegistrarse.start, margin = -10.dp)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = MediumGreen
            )
        ) {
            Text("Iniciar Sesión", fontSize = 15.sp)
        }
    }
}
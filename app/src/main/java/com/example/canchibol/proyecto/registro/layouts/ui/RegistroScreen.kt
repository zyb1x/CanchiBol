package com.example.canchibol.proyecto.registro.layouts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.proyecto.registro.layouts.viewmodel.RegistroViewModel
import com.example.canchibol.ui.theme.*

@Composable
fun LayoutRegistro(
    modifier: Modifier,
    viewModel: RegistroViewModel = viewModel(),
    onVolverLogin: () -> Unit //para regresar
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
            textFieldContrasenia, textFieldConfContra, btnRegistrarse, btnIniciarSesion, errorText) = createRefs()

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

        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.onNombreChange(it) },
            modifier = Modifier.width(300.dp).constrainAs(textFieldNombre) {
                top.linkTo(logo.bottom, margin = 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            placeholder = { Text("Nombre(s)", fontSize = 14.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(10.dp)
        )

        OutlinedTextField(
            value = noEmpleado,
            onValueChange = { viewModel.onNoEmpleadoChange(it) },
            modifier = Modifier.width(300.dp).constrainAs(textFieldNEmpleado) {
                top.linkTo(textFieldNombre.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            placeholder = { Text("Número de empleado", fontSize = 14.sp) },
            singleLine = true
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { viewModel.onCorreoChange(it) },
            modifier = Modifier.width(300.dp).constrainAs(textFieldCorreo) {
                top.linkTo(textFieldNEmpleado.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            placeholder = { Text("Correo", fontSize = 14.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = contrasenia,
            onValueChange = { viewModel.onContraseniaChange(it) },
            modifier = Modifier.width(300.dp).constrainAs(textFieldContrasenia) {
                top.linkTo(textFieldCorreo.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            placeholder = { Text("Contraseña", fontSize = 14.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { viewModel.alternarMostrarPassword() }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            shape = RoundedCornerShape(10.dp)
        )

        OutlinedTextField(
            value = confContra,
            onValueChange = { viewModel.onConfirmarContraseniaChange(it) },
            modifier = Modifier.width(300.dp).constrainAs(textFieldConfContra) {
                top.linkTo(textFieldContrasenia.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            placeholder = { Text("Confirmar contraseña", fontSize = 14.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { viewModel.alternarMostrarPassword() }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            shape = RoundedCornerShape(10.dp)
        )

        Button(
            onClick = { viewModel.validarRegistro() },
            modifier = Modifier.width(300.dp).height(45.dp).constrainAs(btnRegistrarse) {
                top.linkTo(textFieldConfContra.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MediumGreen, contentColor = LightGreen)
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

        Button(
            onClick = { onVolverLogin() },
            modifier = Modifier.width(160.dp).height(45.dp).constrainAs(btnIniciarSesion) {
                top.linkTo(btnRegistrarse.bottom, margin = 60.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen, contentColor = MediumGreen)
        ) {
            Text("Iniciar Sesión", fontSize = 15.sp)
        }
    }
}

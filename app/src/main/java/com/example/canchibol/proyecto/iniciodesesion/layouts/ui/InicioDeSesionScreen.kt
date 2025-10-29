package com.example.canchibol.proyecto.iniciodesesion.layouts.ui

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
import com.example.canchibol.proyecto.iniciodesesion.layouts.viewmodel.InicioDeSesionViewModel
import com.example.canchibol.ui.theme.*

@Composable
fun LayoutIniciarSesion(
    modifier: Modifier,
    viewModel: InicioDeSesionViewModel = viewModel(),
    onIrARegistro: () -> Unit // para cambiar a registro tambien
) {
    val usuario by viewModel.usuario
    val contrasenia by viewModel.contrasenia
    val showPassword by viewModel.mostrarPassword
    val errorMensaje = viewModel.errorMensaje.value

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (logo, textFieldUsuario, textFieldContrasenia, btnIniciarSesion, btnRegistrarse, divider, errorText) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.logo_canchas),
            contentDescription = "Logo Aplicacion",
            modifier = Modifier
                .size(150.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )

        OutlinedTextField(
            value = usuario,
            onValueChange = { viewModel.onUsuarioChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldUsuario) {
                    top.linkTo(logo.bottom, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Correo / Usuario", fontSize = 14.sp, lineHeight = 14.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

        OutlinedTextField(
            value = contrasenia,
            onValueChange = { viewModel.onContraseniaChange(it) },
            modifier = Modifier
                .width(300.dp)
                .constrainAs(textFieldContrasenia) {
                    top.linkTo(textFieldUsuario.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Contraseña", fontSize = 14.sp, lineHeight = 14.sp) },
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
            onClick = { viewModel.validarLogin() },
            modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .constrainAs(btnIniciarSesion) {
                    top.linkTo(textFieldContrasenia.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )
        ) {
            Text("Iniciar Sesión", fontSize = 15.sp)
        }

        if (errorMensaje != null) {
            Text(
                text = errorMensaje,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(errorText) {
                    top.linkTo(btnIniciarSesion.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(divider) {
                    top.linkTo(btnIniciarSesion.bottom, margin = 60.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray, thickness = 1.dp)
            Text(text = "o", modifier = Modifier.padding(horizontal = 18.dp), color = Color.Gray, fontSize = 14.sp)
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray, thickness = 1.dp)
        }

        Button(
            onClick = { onIrARegistro() },
            modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .constrainAs(btnRegistrarse) {
                    top.linkTo(divider.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = MediumGreen
            )
        ) {
            Text("Registrarse", fontSize = 15.sp)
        }
    }
}

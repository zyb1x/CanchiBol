package com.example.canchibol.proyecto.registro.layouts

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.canchibol.R
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.GrayGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen

@Composable
fun LayoutRegistro(modifier: Modifier){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        var nombre by remember { mutableStateOf("") }
        var noEmpleado by remember { mutableStateOf("") }
        var correo by remember { mutableStateOf("") }
        var contrasenia by remember { mutableStateOf("") }
        var confContra by remember { mutableStateOf("") }
        var cargo by remember { mutableStateOf("") }
        var showPassword by remember { mutableStateOf(false) }

        var(logo, textFieldNombre, textFieldNEmpleado, textFieldContrasenia, textFieldConfContra,
            puesto, textFieldCorreo, btnRegistrarse, btnIniciarSesion) = createRefs()

        Image( //logo
            painter = painterResource(id = R.drawable.logo_canchas),
            contentDescription = "Logo Aplicacion",
            modifier = Modifier
                .size(90.dp)
                .constrainAs(logo){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 20.dp)
                }
        )

        OutlinedTextField( //Textfield nombre
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier
                .width(300.dp)

                //.height(46.dp)
                .constrainAs(textFieldNombre){
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

        OutlinedTextField( //Textfield numero de empleado
            value = noEmpleado,
            onValueChange = { noEmpleado = it },
            modifier = Modifier
                .width(300.dp)
                //.height(46.dp)

                .constrainAs(textFieldNEmpleado){
                    top.linkTo(textFieldNombre.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text(
                    "Numero de empleado",
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

        OutlinedTextField( //Textfield numero de empleado
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier
                .width(300.dp)
                //.height(46.dp)
                .constrainAs(textFieldCorreo){
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


        OutlinedTextField( //textfield de contraseña
            value = contrasenia,
            onValueChange = { contrasenia = it },
            modifier = Modifier
                .width(300.dp)
                //.height(46.dp)

                .constrainAs(textFieldContrasenia){
                    top.linkTo(textFieldCorreo.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text(
                    "Contraseña", modifier = Modifier
                        ,
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
            visualTransformation = if (showPassword) { //icono mostrar / ocultar contraseña
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = { showPassword = !showPassword }
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

        OutlinedTextField( //textfield de confirmar contraseña
            value = confContra,
            onValueChange = { confContra = it },
            modifier = Modifier
                .width(300.dp)
                //.height(46.dp)
                .constrainAs(textFieldConfContra){
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
            visualTransformation = if (showPassword) { //icono mostrar / ocultar contraseña
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = { showPassword = !showPassword }
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

        Button(onClick = {/*accion del boton*/}, //boton registrarse
            modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .constrainAs(btnRegistrarse){
                    top.linkTo(textFieldConfContra.bottom, margin = 65.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )) {
            Text("Registrarse", fontSize = 15.sp)
        }

        Button(onClick = {/*accion del boton*/}, //boton inicio de sesion
            modifier = Modifier
                .width(158.dp)
                .height(45.dp)
                .constrainAs(btnIniciarSesion){
                    top.linkTo(btnRegistrarse.bottom, margin = 95.dp)
                    start.linkTo(btnRegistrarse.start, margin = -10.dp)

                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = MediumGreen
            )) {
            Text("Iniciar Sesión", fontSize = 15.sp)
        }


    }
}
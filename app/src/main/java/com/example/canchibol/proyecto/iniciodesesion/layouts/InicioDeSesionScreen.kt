package com.example.canchibol.proyecto.iniciodesesion.layouts


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.canchibol.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.unit.sp
import com.example.canchibol.ui.theme.LightGreen
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.GrayGreen
import com.example.canchibol.ui.theme.MediumGreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation



@Composable
fun LayoutIniciarSesion(modifier: Modifier){
    ConstraintLayout (modifier = Modifier.fillMaxSize()){

        var usuario by remember { mutableStateOf("") }
        var contrasenia by remember { mutableStateOf("") }
        var showPassword by remember { mutableStateOf(false) }

        var(logo, textFieldUsuario, textFieldContrasenia,
            btnIniciarSesion, btnRegistrarse, divider) = createRefs()

        Image( //imagen
            painter = painterResource(id = R.drawable.logo_canchas),
            contentDescription = "Logo Aplicacion",
            modifier = Modifier
                .size(150.dp)
                .constrainAs(logo){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )

        OutlinedTextField( //Textfield de usuario
            value = usuario,
            onValueChange = { usuario = it },
            modifier = Modifier
                .width(300.dp)
                //.height(50.dp)
                .constrainAs(textFieldUsuario){
                    top.linkTo(logo.bottom, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = {
                Text(
                    "Correo / Usuario",
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
                //.height(50.dp)
                .constrainAs(textFieldContrasenia){
                    top.linkTo(textFieldUsuario.bottom, margin = 20.dp)
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

        Button(onClick = {/*accion del boton*/}, //boton inicio de sesion
            modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .constrainAs(btnIniciarSesion){
                    top.linkTo(textFieldContrasenia.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MediumGreen,
                    contentColor = LightGreen
                )) {
            Text("Iniciar Sesión", fontSize = 15.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(divider) {
                    top.linkTo(btnIniciarSesion.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(
                modifier = Modifier.weight(1f),
                color = Color.LightGray,
                thickness = 1.dp
            )
            Text(
                text = "o",
                modifier = Modifier.padding(horizontal = 18.dp),
                color = Color.Gray,
                fontSize = 14.sp
            )
            Divider(
                modifier = Modifier.weight(1f),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }

        Button(onClick = {/*accion del boton*/}, //boton registrarse
            modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .constrainAs(btnRegistrarse){
                    top.linkTo(divider.bottom, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = MediumGreen
            )) {
            Text("Registrarse", fontSize = 15.sp)
        }
    }
}

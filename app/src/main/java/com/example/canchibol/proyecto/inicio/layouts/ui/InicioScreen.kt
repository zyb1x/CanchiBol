package com.example.canchibol.proyecto.inicio.layouts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.proyecto.inicio.layouts.viewmodel.InicioViewModel
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen

@Composable
fun LayoutInicio(modifier: Modifier, viewModel: InicioViewModel = viewModel()) {
    val navegarIniciarSesion by viewModel.navegarIniciarSesion
    val navegarRegistro by viewModel.navegarRegistro

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (btnIniciarSesion, btnRegistrarse, logo, textoBienvenido) = createRefs()

        Text(
            text = "Bienvenido",
            modifier = Modifier.constrainAs(textoBienvenido) {
                top.linkTo(parent.top, margin = 90.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            fontSize = 45.sp,
            fontWeight = FontWeight.SemiBold,
            color = MediumGreen
        )

        Image(
            painter = painterResource(id = R.drawable.logo_canchas),
            contentDescription = "Logo aplicacion",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textoBienvenido.bottom, margin = 125.dp)
                }
        )

        Button(
            onClick = { viewModel.irAIniciarSesion() }, // conectado al ViewModel
            modifier = Modifier
                .width(140.dp)
                .height(45.dp)
                .constrainAs(btnIniciarSesion) {
                    top.linkTo(logo.bottom, margin = 150.dp)
                    start.linkTo(parent.start, margin = -160.dp)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )
        ) {
            Text(text = "Iniciar sesión", fontSize = 15.sp)
        }

        Button(
            onClick = { viewModel.irARegistro() }, // conectado al ViewModel
            modifier = Modifier
                .width(140.dp)
                .height(45.dp)
                .constrainAs(btnRegistrarse) {
                    start.linkTo(btnIniciarSesion.end, margin = 16.dp)
                    top.linkTo(logo.bottom, margin = 150.dp)
                },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = MediumGreen
            )
        ) {
            Text(text = "Registrarse",
                fontSize = 15.sp)
        }
    }
}

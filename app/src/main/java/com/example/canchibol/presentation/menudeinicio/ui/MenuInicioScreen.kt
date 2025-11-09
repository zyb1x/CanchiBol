package com.example.canchibol.proyecto.menudinicio.layouts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.proyecto.menudinicio.layouts.viewmodel.MenuInicioViewModel
import com.example.canchibol.ui.theme.*

@Composable
fun LayoutMenuInicio(
    modifier: Modifier,
    viewModel: MenuInicioViewModel = viewModel(),
    onCerrarSesion: () -> Unit,
    onNavigateToAgendarPartido: () -> Unit,
    onNavigateToCanchas: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onNavigateToReporte: () -> Unit
) {
    val opcionSeleccionada by viewModel.opcionSeleccionada


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {Surface(
        modifier = Modifier
            .height(130.dp)
            .width(300.dp),
        onClick = { viewModel.seleccionarOpcion("Agendar partido")
                  onNavigateToAgendarPartido()},
        shadowElevation = 2.dp, shape = RoundedCornerShape(10.dp), color = LightGreen,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Icono mas",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = "Agendar partido",
                    modifier = Modifier.padding(top = 90.dp),
                    color = DarkGreen,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(300.dp),
                onClick = { viewModel.seleccionarOpcion("Canchas")
                          onNavigateToCanchas()}, // ✅ conectado
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = MediumGreen
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancha_icon),
                        contentDescription = "Icono cancha",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(bottom = 10.dp)
                    )
                    Text(
                        text = "Canchas",
                        modifier = Modifier.padding(top = 90.dp),
                        color = LightGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))


            //  Calendario
            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(300.dp),
                onClick = { viewModel.seleccionarOpcion("Calendario")
                          onNavigateToCalendario()}, // ✅ conectado
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = LightGreen
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

            Spacer(modifier = Modifier.height(10.dp))

            //Reportr
            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(300.dp),
                onClick = { viewModel.seleccionarOpcion("Reporte")
                          onNavigateToReporte()}, //  conectado
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = MediumGreen
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.reporte_icon_ligth),
                        contentDescription = "Icono reporte",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(bottom = 10.dp)
                    )
                    Text(
                        text = "Reporte",
                        modifier = Modifier.padding(top = 90.dp),
                        color = LightGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        Spacer(modifier = Modifier.height(15.dp))


        Button(
            onClick = { onCerrarSesion() },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )
        ) {
            Text("Cerrar sesión", fontSize = 16.sp)
        }
        }



    }


private fun ColumnScope.onCerrarSesion() {
    TODO("Not yet implemented")
}

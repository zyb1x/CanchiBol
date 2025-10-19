package com.example.canchibol.proyecto.menuDeInicio.layouts.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.R
import com.example.canchibol.ui.theme.MediumGreen


@Composable
fun LayoutMenuInicio(
    modifier: Modifier
    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    Surface(
        modifier = Modifier
            .height(130.dp)
            .width(300.dp)
            ,
        onClick = {},
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(10.dp),
        color = LightGreen,

    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
            ){
            Image(
                painter = painterResource(id = R.drawable.add_icon),
                contentDescription = "Icono mas",
                modifier = Modifier
                    .size(50.dp)
                    .padding(bottom = 10.dp)
            )
            Text(text = "Agendar partido" ,modifier = Modifier
                .padding(top = 90.dp),
                color = DarkGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,

            )
        }

    }
        Spacer( modifier = Modifier.height(38.dp))

        Row (){
            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp),
                onClick = {},
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = MediumGreen
            ) {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.cancha_icon),
                        contentDescription = "Icono cancha",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(bottom = 10.dp)
                    )
                    Text(text = "Canchas" ,modifier = Modifier
                        .padding(top = 90.dp),
                        color = LightGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,

                        )
                }
            }

            Spacer(modifier = Modifier.width(38.dp))

            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp),
                onClick = {},
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = LightGreen
            ) {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.arbitro_icon_dark),
                        contentDescription = "Icono arbitro",
                        modifier = Modifier
                            .size(75.dp)
                            .padding(bottom = 10.dp)
                    )
                    Text(text = "Registrar arbitro" ,modifier = Modifier
                        .padding(top = 90.dp),
                        color = DarkGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,

                        )
                }
            }


        }

        Spacer(modifier= Modifier.height(38.dp))

        Row (){
            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp),
                onClick = {},
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = LightGreen
            ) {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.calenda_icon_dark),
                        contentDescription = "Icono calendario",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(bottom = 10.dp)
                    )
                    Text(text = "Calendario" ,modifier = Modifier
                        .padding(top = 90.dp),
                        color = DarkGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,

                        )
                }
            }

            Spacer(modifier = Modifier.width(38.dp))

            Surface(
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp),
                onClick = {},
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = MediumGreen
            ) {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.reporte_icon_ligth),
                        contentDescription = "Icono reporte",
                        modifier = Modifier
                            .size(55.dp)
                            .padding(bottom = 10.dp)
                    )
                    Text(text = "Reporte" ,modifier = Modifier
                        .padding(top = 90.dp),
                        color = LightGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,

                        )
                }
            }


        }

} }
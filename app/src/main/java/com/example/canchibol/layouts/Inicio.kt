package com.example.canchibol.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen

@Preview
@Composable
fun Btones(){
    Box() {
        Button(
            onClick = {}, modifier = Modifier
                .width(140.dp)
                .height(45.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumGreen,
                contentColor = LightGreen
            )
        ) {
            Text(
                text = "Inciar sesión",
                fontSize = 15.sp
            )
        }

        Button(onClick = {}, modifier = Modifier
            .width(140.dp)
            .height(45.dp),) {
            Text(text = "Registrarse")
        }
    }
}
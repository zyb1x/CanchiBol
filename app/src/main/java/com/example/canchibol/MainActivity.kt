package com.example.canchibol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.canchibol.proyecto.inicio.layouts.ui.LayoutInicio
import com.example.canchibol.proyecto.iniciodesesion.layouts.LayoutIniciarSesion
import com.example.canchibol.proyecto.menuDeInicio.layouts.ui.LayoutMenuInicio
import com.example.canchibol.proyecto.registro.layouts.LayoutRegistro


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutInicio(modifier = Modifier)
            //LayoutIniciarSesion(modifier = Modifier)
            //LayoutRegistro(modifier = Modifier)
            //LayoutMenuInicio(modifier = Modifier)
        }
    }
}



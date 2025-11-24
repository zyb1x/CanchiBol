package com.example.canchibol.presentation.perfil.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.canchibol.R
import com.example.canchibol.presentation.perfil.viewmodel.PerfilViewModel
import com.example.canchibol.presentation.perfil.viewmodel.PerfilViewModelFactory
import com.example.canchibol.ui.theme.DarkGreen
import com.example.canchibol.ui.theme.LightGreen
import com.example.canchibol.ui.theme.MediumGreen
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier,
    onNavigateToInicio: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onNavigateToProximosPartidos: () -> Unit,
    onNavigateToCalendario: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: PerfilViewModel = viewModel(factory = PerfilViewModelFactory(context))
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val user by viewModel.user

    var tempImageUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                tempImageUri?.let { viewModel.saveProfileImage(it) }
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val file = File(context.cacheDir, "${UUID.randomUUID()}.jpg")
                val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                tempImageUri = uri
                cameraLauncher.launch(uri)
            } 
        }
    )

    ModalNavigationDrawer(
        drawerContent = { /* ... */ },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = { /* ... */ }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier.size(120.dp).clip(CircleShape).background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    val imageUri = user?.profileImageUrl?.let { Uri.parse(it) }
                    if (imageUri != null) {
                        val bitmap = if (Build.VERSION.SDK_INT < 28) {
                            android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
                        } else {
                            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
                            ImageDecoder.decodeBitmap(source)
                        }
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Foto de perfil",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permission == PackageManager.PERMISSION_GRANTED) {
                        val file = File(context.cacheDir, "${UUID.randomUUID()}.jpg")
                        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                        tempImageUri = uri
                        cameraLauncher.launch(uri)
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }) {
                    Text("Editar Foto")
                }

                Spacer(modifier = Modifier.height(24.dp))

                user?.let { 
                    Text(text = it.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Correo: ${it.email}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Nº de Empleado: ${it.numEmpleado}", style = MaterialTheme.typography.bodyLarge)
                 }
            }
        }
    }
}
package com.example.canchibol.presentation.perfil.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.canchibol.data.session.SessionManager

class PerfilViewModel(context: Context) : ViewModel() {

    private val sharedPreferences = context.getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)
    private val PREF_IMAGE_URI = "profile_image_uri"

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    init {
        loadUserData()
    }

    private fun loadUserData() {
        val savedImageUri = sharedPreferences.getString(PREF_IMAGE_URI, null)
        SessionManager.currentUser?.let {
            _user.value = User(
                name = "${it.nombre} ${it.apellidoPaterno} ${it.apellidoMaterno}",
                email = it.correo,
                numEmpleado = it.numEmpleado,
                profileImageUrl = savedImageUri
            )
        }
    }

    fun saveProfileImage(uri: Uri) {
        sharedPreferences.edit().putString(PREF_IMAGE_URI, uri.toString()).apply()
        _user.value = _user.value?.copy(profileImageUrl = uri.toString())
    }
}

data class User(val name: String, val email: String, val numEmpleado: String, val profileImageUrl: String? = null)
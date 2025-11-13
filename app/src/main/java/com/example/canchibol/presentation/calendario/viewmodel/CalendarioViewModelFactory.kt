
package com.example.canchibol.presentation.calendario.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.canchibol.data.repository.PartidoRepositoryImpl

class CalendarioViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarioViewModel::class.java)) {
            val repository = PartidoRepositoryImpl(context)
            @Suppress("UNCHECKED_CAST")
            return CalendarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
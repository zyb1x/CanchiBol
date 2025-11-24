package com.example.canchibol.presentation.proximospartidos.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canchibol.domain.entity.PartidoEntity
import com.example.canchibol.domain.usecase.GetProximosPartidosUseCase
import kotlinx.coroutines.launch

data class ProximosPartidosUiState(
    val isLoading: Boolean = true,
    val partidos: List<PartidoEntity> = emptyList(),
    val error: String? = null
)

class ProximosPartidosViewModel(private val getProximosPartidosUseCase: GetProximosPartidosUseCase) : ViewModel() {

    private val _uiState = mutableStateOf(ProximosPartidosUiState())
    val uiState: State<ProximosPartidosUiState> = _uiState

    init {
        loadProximosPartidos()
    }

    private fun loadProximosPartidos() {
        viewModelScope.launch {
            _uiState.value = ProximosPartidosUiState(isLoading = true)
            val result = getProximosPartidosUseCase()
            result.onSuccess {
                _uiState.value = ProximosPartidosUiState(partidos = it, isLoading = false)
            }.onFailure {
                _uiState.value = ProximosPartidosUiState(error = it.message, isLoading = false)
            }
        }
    }
}

// presentation/calendario/viewmodel/CalendarioViewModel.kt
package com.example.canchibol.presentation.calendario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canchibol.data.repository.PartidoRepository
import com.example.canchibol.domain.entity.PartidoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CalendarioViewModel(
    private val partidoRepository: PartidoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalendarioUiState())
    val uiState: StateFlow<CalendarioUiState> = _uiState.asStateFlow()

    fun showDatePicker() {
        _uiState.value = _uiState.value.copy(showDatePicker = true)
    }

    fun hideDatePicker() {
        _uiState.value = _uiState.value.copy(showDatePicker = false)
    }

    fun onDateSelected(dateMillis: Long?) {
        if (dateMillis != null) {
            // Verificar si es viernes, sábado o domingo
            if (esFinDeSemana(dateMillis)) {
                _uiState.value = _uiState.value.copy(
                    selectedDate = dateMillis,
                    showDatePicker = false,
                    isLoading = true
                )
                loadPartidos(dateMillis)
            } else {
                _uiState.value = _uiState.value.copy(
                    selectedDate = dateMillis,
                    showDatePicker = false,
                    partidos = emptyList(),
                    error = "Solo puedes seleccionar viernes, sábados y domingos"
                )
            }
        } else {
            _uiState.value = _uiState.value.copy(
                selectedDate = null,
                showDatePicker = false,
                partidos = emptyList()
            )
        }
    }

    private fun loadPartidos(dateMillis: Long) {
        viewModelScope.launch {
            val fechaFormateada = formatDateForApi(dateMillis)
            val result = partidoRepository.getPartidosPorFecha(fechaFormateada)

            _uiState.value = when {
                result.isSuccess -> {
                    _uiState.value.copy(
                        partidos = result.getOrNull() ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                else -> {
                    _uiState.value.copy(
                        partidos = emptyList(),
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Error al cargar partidos"
                    )
                }
            }
        }
    }

    private fun esFinDeSemana(dateMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateMillis
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek == Calendar.FRIDAY ||
                dayOfWeek == Calendar.SATURDAY ||
                dayOfWeek == Calendar.SUNDAY
    }

    private fun formatDateForApi(dateMillis: Long): String {
        val date = java.util.Date(dateMillis)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(date)
    }
}

data class CalendarioUiState(
    val showDatePicker: Boolean = false,
    val selectedDate: Long? = null,
    val isLoading: Boolean = false,
    val partidos: List<PartidoEntity> = emptyList(),
    val error: String? = null
)
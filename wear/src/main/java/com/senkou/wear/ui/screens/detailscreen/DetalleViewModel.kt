package com.senkou.wear.ui.screens.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.common.obtenerDiasSesiones
import com.senkou.domain.model.Sesion
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.wear.ui.common.stateAsResultIn
import kotlinx.coroutines.flow.map

class DetalleViewModel(
   idEspectaculo: Int,
   cargarDetalle: CargarDetalleUseCase,
) : ViewModel() {

   val uiState = cargarDetalle(idEspectaculo).map { sesiones ->
      UiState(sesiones = sesiones, sesiones.obtenerDiasSesiones())
   }.stateAsResultIn(viewModelScope)

   data class UiState(
      val sesiones: List<Sesion> = emptyList(),
      val diasSesiones: List<String> = emptyList(),
   )
//
//   private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
//   val uiState = _uiState.asStateFlow()
//
//   init {
//      viewModelScope.launch {
//         _uiState.update {
//            it.copy(sesiones = cargarDetalle(idEspectaculo))
//
//         }
//      }
//   }
//
//   fun obtenerDiasSesiones(): List<String> {
//      val fechasSpinner = arrayListOf<String>()
//      uiState.value.sesiones.forEach { sesion ->
//         sesion.diacompleto.let {
//            if (!fechasSpinner.contains(it)) {
//               fechasSpinner.add(it)
//            }
//         }
//      }
//
//      if (fechasSpinner.isNotEmpty()) {
//         fechasSpinner.sort()
//         fechasSpinner.ordenarMeses()
//      }
//      return fechasSpinner
//   }
//
//   data class UiState(
//      val sesiones: List<Sesion> = emptyList()
//   )
}
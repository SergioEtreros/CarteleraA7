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
}
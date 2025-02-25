package com.senkou.carteleraa7.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.ui.common.stateAsResultIn
import com.senkou.domain.common.obtenerDiasSesiones
import com.senkou.domain.model.Sesion
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.ReproducirTrailerUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

//@HiltViewModel
class DetalleViewModel(
   idEspectaculo: Int,
   cargarDetalle: CargarDetalleUseCase,
   private val reproducirTrailer: ReproducirTrailerUseCase,
) : ViewModel() {

   val uiState = cargarDetalle(idEspectaculo).map { sesiones ->
      UiState(sesiones = sesiones, sesiones.obtenerDiasSesiones())
   }.stateAsResultIn(viewModelScope)

   fun playTrailer(video: String) {
      viewModelScope.launch {
         reproducirTrailer(video)
      }
   }

   data class UiState(
      val sesiones: List<Sesion> = emptyList(),
      val diasSesiones: List<String> = emptyList(),
   )
}

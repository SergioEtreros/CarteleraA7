package com.senkou.tv.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.common.ordenarMeses
import com.senkou.domain.model.Sesion
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.ReproducirTrailerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//@HiltViewModel
class DetalleViewModel(
   private val idEspectaculo: Int,
   private val cargarDetalle: CargarDetalleUseCase,
   private val reproducirTrailer: ReproducirTrailerUseCase,
) : ViewModel() {

   private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
   val uiState = _uiState.asStateFlow()

   init {
      viewModelScope.launch {
         _uiState.update {
            it.copy(sesiones = cargarDetalle(idEspectaculo))

         }
      }
   }

   fun obtenerDiasSesiones(): List<String> {
      val fechasSpinner = arrayListOf<String>()
      uiState.value.sesiones.forEach { sesion ->
         sesion.diacompleto.let {
            if (!fechasSpinner.contains(it)) {
               fechasSpinner.add(it)
            }
         }
      }

      if (fechasSpinner.isNotEmpty()) {
         fechasSpinner.sort()
         fechasSpinner.ordenarMeses()
      }
      return fechasSpinner
   }

   fun playTrailer(video: String) {
      reproducirTrailer(video)
   }

   data class UiState(
      val sesiones: List<Sesion> = emptyList()
   )
}


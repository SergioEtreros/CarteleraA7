package com.senkou.carteleraa7.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.common.ordenarMeses
import com.senkou.domain.model.Sesion
import com.senkou.usecases.CargarBackgroundUseCase
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.ReproducirTrailerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URLEncoder

//@HiltViewModel
class DetalleViewModel(
   private val idEspectaculo: Int,
   private val cargarDetalle: CargarDetalleUseCase,
   private val cargarBackground: CargarBackgroundUseCase,
   private val reproducirTrailer: ReproducirTrailerUseCase,
) : ViewModel() {

   private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
   val uiState = _uiState.asStateFlow()

   init {
      viewModelScope.launch {

         val sesiones = cargarDetalle(idEspectaculo)

         val diasSesiones = obtenerDiasSesiones(sesiones)

         val background = cargarBackground(
            sesiones.first().tituloOriginal,
            sesiones.first().fechaEstrenoSpanish.substringBefore("-")
         ) ?: sesiones.first().cartel

         if (sesiones.isNotEmpty()) {
            _uiState.update {
               it.copy(
                  sesiones = sesiones,
                  diasSesiones = diasSesiones,
                  background = URLEncoder.encode(background, "UTF-8")
               )
            }
         }
      }
   }

   private fun obtenerDiasSesiones(sesiones: List<Sesion>): List<String> {
      val fechasSpinner = arrayListOf<String>()
      sesiones.forEach { sesion ->
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

   fun playTrailer() {
      viewModelScope.launch {
         reproducirTrailer(uiState.value.sesiones.first().video)
      }
   }

   data class UiState(
      val sesiones: List<Sesion> = emptyList(),
      val diasSesiones: List<String> = emptyList(),
      val background: String = ""
   )
}


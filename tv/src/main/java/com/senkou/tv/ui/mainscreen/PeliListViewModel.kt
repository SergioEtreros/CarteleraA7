package com.senkou.tv.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.model.Pelicula
import com.senkou.usecases.CargarBackgroundUseCase
import com.senkou.usecases.CargarCarteleraUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//@HiltViewModel
class PeliListViewModel(
   private val cargarCarteleraUseCase: CargarCarteleraUseCase,
   private val cargarBackgroundUseCase: CargarBackgroundUseCase,
) : ViewModel() {

   private val _state = MutableStateFlow(UiState(background = ""))
   val state get() = _state.asStateFlow()

   init {
      viewModelScope.launch {
         val cartelera = cargarCarteleraUseCase()
         _state.update {
            it.copy(
               peliculas = cartelera.peliculas,
               proximosEstrenos = cartelera.proximosEstrenos
            )
         }

         if (cartelera.peliculas.isNotEmpty()) {
            updateBackground(cartelera.peliculas.first())
         }
      }
   }

   fun updateBackground(pelicula: Pelicula) {
      viewModelScope.launch {
         _state.update {
            it.copy(
               background = cargarBackgroundUseCase(
                  pelicula.tituloOriginal,
                  pelicula.fechaEstreno.substringBefore("-")
               ) ?: pelicula.cartel,
            )
         }
      }
   }

   data class UiState(
      val peliculas: List<Pelicula> = emptyList(),
      val proximosEstrenos: List<Pelicula> = emptyList(),
      val background: String,
   )
}
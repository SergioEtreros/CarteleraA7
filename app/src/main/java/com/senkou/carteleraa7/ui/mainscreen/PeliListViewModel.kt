package com.senkou.carteleraa7.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.domain.model.Pelicula
import com.senkou.carteleraa7.usecase.CargarCarteleraUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//@HiltViewModel
class PeliListViewModel(
   private val cargarCarteleraUseCase: CargarCarteleraUseCase
) : ViewModel() {

   private val _state = MutableStateFlow(UiState())
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
      }
   }

   data class UiState(
      val peliculas: List<Pelicula> = emptyList(),
      val proximosEstrenos: List<Pelicula> = emptyList()
   )
}
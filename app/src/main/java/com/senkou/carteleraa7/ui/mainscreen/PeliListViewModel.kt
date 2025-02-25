package com.senkou.carteleraa7.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.ui.common.stateAsResultIn
import com.senkou.domain.model.Pelicula
import com.senkou.usecases.CargarPeliculasUseCase
import com.senkou.usecases.CargarProximosEstrenosUseCase
import kotlinx.coroutines.flow.combine

//@HiltViewModel
class PeliListViewModel(
   cargarPeliculasUseCase: CargarPeliculasUseCase,
   cargarProximosEstrenosUseCase: CargarProximosEstrenosUseCase
) : ViewModel() {


   val state = cargarPeliculasUseCase()
      .combine(cargarProximosEstrenosUseCase()) { cartelera, proximosEstrenos ->
         UiState(cartelera,proximosEstrenos)
      }.stateAsResultIn(viewModelScope)

   data class UiState(
      val peliculas: List<Pelicula> = emptyList(),
      val proximosEstrenos: List<Pelicula> = emptyList()
   )
}
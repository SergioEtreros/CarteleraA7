package com.senkou.wear.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.usecases.CargarPeliculasUseCase
import com.senkou.wear.ui.common.stateAsResultIn

//@HiltViewModel
class PeliViewModel(
   cargarPeliculasUseCase: CargarPeliculasUseCase,
) : ViewModel() {

   val state = cargarPeliculasUseCase().stateAsResultIn(viewModelScope)
}
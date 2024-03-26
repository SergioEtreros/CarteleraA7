package com.senkou.carteleraa7.framework.ui.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.domain.Pelicula
import com.senkou.carteleraa7.domain.Sesion
import com.senkou.carteleraa7.usecase.CargarCartelera
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeliViewModel @Inject constructor(private val cargarCartelera: CargarCartelera) :
   ViewModel() {

   private val _peliculas = MutableLiveData<List<Pelicula>>()
   val peliculas: LiveData<List<Pelicula>> get() = _peliculas

   private val _sesiones = MutableLiveData<List<Sesion>>()

   private val _proximosEstrenos = MutableLiveData<List<Pelicula>>()

   fun getPeliculas() = peliculas.value.orEmpty()

   fun getProximosEstrenos() = _proximosEstrenos.value.orEmpty()

   fun getSesiones(idPelicula: Int): List<Sesion> =
      _sesiones.value?.filter { it.iDEspectaculo == idPelicula }.orEmpty()

   fun cargarCartelera() {

      viewModelScope.launch(Dispatchers.Main) {
         val cartelera = cargarCartelera.invoke()
         _peliculas.value = cartelera.pelis
         _sesiones.value = cartelera.sesiones
         _proximosEstrenos.value = cartelera.proximosEstrenos
      }
   }
}
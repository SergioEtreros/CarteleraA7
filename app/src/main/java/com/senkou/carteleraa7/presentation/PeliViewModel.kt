package com.senkou.carteleraa7.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.data.DataA7
import com.senkou.carteleraa7.data.Utilidades
import com.senkou.carteleraa7.data.model.Pelicula
import com.senkou.carteleraa7.data.model.Sesion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@HiltViewModel
class PeliViewModel : ViewModel() {

   var dataA7: DataA7? = null

   val peliculas: MutableLiveData<MutableList<Pelicula>> = MutableLiveData<MutableList<Pelicula>>()

   private val sesiones: MutableLiveData<MutableList<Sesion>> =
      MutableLiveData<MutableList<Sesion>>()

   val proximosEstrenos: MutableLiveData<MutableList<Pelicula>> =
      MutableLiveData<MutableList<Pelicula>>()

   fun getPeliculas() = peliculas.value.orEmpty()

   fun getSesiones(idPelicula: Int): List<Sesion> =
      sesiones.value?.filter { it.iDEspectaculo == idPelicula }.orEmpty()

   fun cargarCartelera() {

      viewModelScope.launch(Dispatchers.IO) {
         val response = dataA7?.obtenerCartelera()
         peliculas.postValue(response?.pelis?.toMutableList())
         sesiones.postValue(response?.sesiones?.toMutableList())
         proximosEstrenos.postValue(response?.proximosEstrenos?.toMutableList())
      }
   }

   fun obtenerDiasSesiones(sesiones: List<Sesion>): List<String> {
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
         Utilidades.ordenarMeses(fechasSpinner)
      }
      return fechasSpinner
   }
}
package com.senkou.carteleraa7.framework.ui.detail

import androidx.lifecycle.ViewModel
import com.senkou.carteleraa7.domain.Sesion
import com.senkou.carteleraa7.framework.common.Utilidades
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel(private val sesiones: List<Sesion>) : ViewModel() {

   @Inject
   constructor() : this(emptyList())

   fun getSesiones(): List<Sesion> = sesiones

   fun obtenerDiasSesiones(): List<String> {
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
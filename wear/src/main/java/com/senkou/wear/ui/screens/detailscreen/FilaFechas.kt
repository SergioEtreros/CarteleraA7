package com.senkou.wear.ui.screens.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senkou.wear.data.model.Sesion
import com.senkou.wear.ui.screens.mainscreen.PeliViewModel

@Composable
fun FilaFechas(sesiones: List<Sesion>, model: PeliViewModel = viewModel()) {

   val lista = model.obtenerDiasSesiones(sesiones)

   LazyRow(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
   ) {
      items(
         items = lista,
         itemContent = { dia ->
            val fecha = if (dia == "Hoy") {
               ""
            } else {
               dia
            }
            InfoSesiones(
               sesiones.first().getUrlCartel(),
               fecha,
               sesiones.filter { it.diacompleto == dia })
         }
      )
   }
}
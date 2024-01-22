package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senkou.carteleraa7.data.Utilidades
import com.senkou.carteleraa7.data.model.Sesion
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FilaFechas(sesiones: List<Sesion>, model: PeliViewModel = viewModel()) {

   val lista = model.obtenerDiasSesiones(sesiones)

   LazyRow(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
   ) {
      items(
         items = lista,
         itemContent = { dia ->

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val hoy = sdf.format(Calendar.getInstance().time)
            val fecha = Utilidades.HOY.takeIf { hoy == dia } ?: dia

            InfoSesiones(
               sesiones.first().getUrlCartel(),
               fecha,
               sesiones.filter { it.diacompleto == dia })
         }
      )
   }
}
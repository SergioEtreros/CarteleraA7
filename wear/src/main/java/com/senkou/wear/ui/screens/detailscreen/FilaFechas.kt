package com.senkou.wear.ui.screens.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.domain.model.Sesion
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FilaFechas(
   sesiones: List<Sesion>,
   lista: List<String>
) {

   LazyRow(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
   ) {
      items(
         items = lista,
         itemContent = { dia ->
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val hoy = sdf.format(Calendar.getInstance().time)
            val fecha = "Hoy".takeIf { hoy == dia } ?: dia

//            val fecha = if (dia == "Hoy") {
//               ""
//            } else {
//               dia
//            }
            InfoSesiones(
               sesiones.first().cartel,
               fecha,
               sesiones.filter { it.diacompleto == dia })
         }
      )
   }
}
package com.senkou.carteleraa7.framework.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.carteleraa7.framework.common.Utilidades
import com.senkou.carteleraa7.framework.common.Utilidades.Companion.getUrlCartel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FilaFechas(model: DetalleViewModel) {

   val lista = model.obtenerDiasSesiones()
   val sesiones = model.getSesiones()

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
               getUrlCartel(sesiones.first().cartel),
               fecha,
               sesiones.filter { it.diacompleto == dia })
         }
      )
   }
}
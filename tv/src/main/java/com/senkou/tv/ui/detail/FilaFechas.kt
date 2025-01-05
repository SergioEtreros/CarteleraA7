package com.senkou.tv.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
   lista: List<String>,
   modifier: Modifier = Modifier
) {
   LazyRow(
      modifier = modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(horizontal = 16.dp)
   ) {
      items(
         items = lista,
         itemContent = { dia ->

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val hoy = sdf.format(Calendar.getInstance().time)
            val fecha = "Hoy".takeIf { hoy == dia } ?: dia

            InfoSesiones(
               sesiones.first().cartel,
               fecha,
               sesiones.filter { it.diacompleto == dia })
         }
      )
   }
}
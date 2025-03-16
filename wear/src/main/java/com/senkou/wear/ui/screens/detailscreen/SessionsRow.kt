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
fun SessionsRow(
   sessions: List<Sesion>,
   sessionsDays: List<String>
) {

   LazyRow(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
   ) {
      items(
         items = sessionsDays,
         itemContent = { day ->
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val today = sdf.format(Calendar.getInstance().time)
            val date = "Hoy".takeIf { today == day } ?: day

            SessionInfo(
               imageUrl = sessions.first().cartel,
               date = date,
               sessions = sessions.filter { it.diacompleto == day })
         }
      )
   }
}
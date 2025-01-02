package com.senkou.tv.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.domain.model.Sesion

@Composable
fun Detalles(sesion: Sesion, modifier: Modifier = Modifier) {

   Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
      DetailTextItem("Título original", sesion.tituloOriginal)
      DetailTextItem("Duración", "${sesion.duracion} min")
      DetailTextItem("Género", sesion.nombreGenero)
      DetailTextItem("Calificación", sesion.nombreCalificacion)
      DetailTextItem("Reparto", sesion.interpretes)
      DetailTextItem("Fecha de estreno", sesion.fechaEstrenoSpanish)
      DetailTextItem("Sinopsis", sesion.sinopsis)
   }
}
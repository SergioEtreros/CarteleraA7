package com.senkou.tv.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.domain.model.Sesion

@Composable
fun Details(session: Sesion, modifier: Modifier = Modifier) {

   Column(modifier = modifier.verticalScroll(state = rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)) {
      DetailTextItem("Título original", session.tituloOriginal)
      DetailTextItem("Duración", "${session.duracion} min")
      DetailTextItem("Género", session.nombreGenero)
      DetailTextItem("Calificación", session.nombreCalificacion)
      DetailTextItem("Reparto", session.interpretes)
      DetailTextItem("Fecha de estreno", session.fechaEstrenoSpanish)
      DetailTextItem("Sinopsis", session.sinopsis)
   }
}
package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.domain.model.Pelicula

@Composable
fun Cartelera(
   lista: List<Pelicula>,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   val state = rememberLazyListState()

   LazyRow(
      modifier = Modifier.fillMaxSize(),
      state = state,
      contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(24.dp)
   ) {

      items(
         items = lista,
         key = { it.idEspectaculo },
         itemContent = { peli ->
            PeliculaItem(pelicula = peli) { idEspectaculo ->
               onMovieClicked(idEspectaculo)
            }
         }
      )
   }
}
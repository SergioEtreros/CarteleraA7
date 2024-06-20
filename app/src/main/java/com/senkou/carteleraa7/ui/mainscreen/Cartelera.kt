package com.senkou.carteleraa7.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.carteleraa7.domain.model.Pelicula
import com.senkou.carteleraa7.ui.theme.fondo_lista

@Composable
fun Cartelera(
   lista: List<Pelicula>,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   val state = rememberLazyListState()

   LazyColumn(
      modifier = Modifier
         .background(fondo_lista)
         .fillMaxSize(),
      state = state,
      contentPadding = PaddingValues(24.dp),
      verticalArrangement = Arrangement.spacedBy(24.dp)
   ) {

      itemsIndexed(
         items = lista,
         itemContent = { _, peli ->
            PeliculaItem(pelicula = peli) { idEspectaculo ->
               onMovieClicked(idEspectaculo)
            }
         }
      )
   }
}
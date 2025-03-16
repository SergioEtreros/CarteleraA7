package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.domain.model.Pelicula

@Composable
fun CartelList(
   lista: List<Pelicula>,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
   onFocus: (Pelicula) -> Unit
) {

   val listState = rememberLazyListState()

   LazyRow(
      modifier = Modifier.fillMaxWidth(),
      state = listState,
      contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 18.dp),
      horizontalArrangement = Arrangement.spacedBy(24.dp)
   ) {
      items(items = lista, key = { it.idEspectaculo }) { peli ->
         MovieItem(
            movie = peli,
            onMovieClicked = { idEspectaculo -> onMovieClicked(idEspectaculo) },
            onFocus = onFocus
         )
      }
   }
}
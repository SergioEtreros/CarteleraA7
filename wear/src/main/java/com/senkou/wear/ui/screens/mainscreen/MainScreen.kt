package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import com.senkou.wear.ui.theme.fondo_lista

@Composable
fun MainScreen(
   model: PeliViewModel,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   val state = model.state.collectAsState().value

   ScalingLazyColumn(
      modifier = Modifier
         .background(fondo_lista),
//            contentPadding = PaddingValues(16.dp)
   ) {

      items(
         items = state.peliculas,
         itemContent = { peli ->
            PeliculaItem(pelicula = peli) { idEspectaculo ->
               onMovieClicked(idEspectaculo)
            }
         }
      )
   }
}

@Preview
@Composable
fun DefaultPreview() {

}
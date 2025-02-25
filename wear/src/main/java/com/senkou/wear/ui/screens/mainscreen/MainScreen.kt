package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material3.Text
import com.senkou.domain.model.Pelicula
import com.senkou.wear.ui.common.LoadingIndicator
import com.senkou.wear.ui.common.Result
import com.senkou.wear.ui.theme.fondo_lista

@Composable
fun MainScreen(
   model: PeliViewModel,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {
   val state by model.state.collectAsStateWithLifecycle()

   MainScreen (state = state, onMovieClicked = onMovieClicked)
}

@Composable
fun MainScreen(
   state: Result<List<Pelicula>>,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   Scaffold {
      when (state) {
         Result.Loading -> LoadingIndicator()
         is Result.Error -> Text(text = state.throwable.message.orEmpty())
         is Result.Success -> {
            ScalingLazyColumn(
               modifier = Modifier
                  .fillMaxSize()
                  .background(fondo_lista),
            ) {
               items(
                  items = state.data,
                  key = { it.idEspectaculo },
                  itemContent = { peli ->
                     PeliculaItem(pelicula = peli) { idEspectaculo ->
                        onMovieClicked(idEspectaculo)
                     }
                  }
               )
            }
         }
      }


   }
}

//@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
//@Composable
//fun DefaultPreview() {
//
//   val moviesRepository = MoviesRepository(WebMovieDatasource())
//   val model = PeliViewModel(CargarCarteleraUseCase(moviesRepository))
//
//   MainScreen(
//      model = model,
//      onMovieClicked = {}
//   )
//}
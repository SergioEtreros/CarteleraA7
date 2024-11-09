package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Scaffold
import androidx.wear.tooling.preview.devices.WearDevices
import com.senkou.data.MoviesRepository
import com.senkou.framework.remote.WebMovieDatasource
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.wear.ui.theme.fondo_lista

@Composable
fun MainScreen(
   model: PeliViewModel,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   val state = model.state.collectAsState().value

   Scaffold {
      ScalingLazyColumn(
         modifier = Modifier
            .fillMaxSize()
            .background(fondo_lista),
      ) {

         items(
            items = state.peliculas,
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

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {

   val moviesRepository = MoviesRepository(WebMovieDatasource())
   val model = PeliViewModel(CargarCarteleraUseCase(moviesRepository))

   MainScreen(
      model = model,
      onMovieClicked = {}
   )
}
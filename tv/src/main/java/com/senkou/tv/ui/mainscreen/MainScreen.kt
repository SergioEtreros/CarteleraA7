package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.senkou.data.MoviesRepository
import com.senkou.tv.ui.Screen
import com.senkou.usecases.CargarCarteleraUseCase

@Composable
fun MainScreen(
   model: PeliListViewModel,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   Screen {
      val state by model.state.collectAsStateWithLifecycle()

      LazyColumn(
         modifier = Modifier
            .fillMaxSize(),
         verticalArrangement = Arrangement.spacedBy(24.dp)
      ) {
         item {
            Text(
               modifier = Modifier.padding(start = 24.dp, top = 16.dp),
               text = "CARTELERA",
               color = Color.White,
               fontWeight = FontWeight.Bold,
               style = MaterialTheme.typography.displayLarge.copy(
                  shadow = Shadow(
                     color = Color.Black,
                     offset = Offset(8f, 8f),
                     blurRadius = 4f
                  )
               )
            )
            Cartelera(state.peliculas) { idEspectaculo -> onMovieClicked(idEspectaculo) }
         }
         item {
            Text(
               modifier = Modifier.padding(start = 24.dp),
               text = "PROX. ESTRENOS",
               color = Color.White,
               fontWeight = FontWeight.Bold,
               style = MaterialTheme.typography.displayLarge.copy(
                  shadow = Shadow(
                     color = Color.Black,
                     offset = Offset(8f, 8f),
                     blurRadius = 4f
                  )

               )
            )
            Cartelera(state.proximosEstrenos) {}

            Spacer(modifier = Modifier.height(24.dp))
         }
      }
   }
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {
   val moviesRepository = MoviesRepository(com.senkou.framework.remote.WebMovieDatasource())
   val peliListViewModel = PeliListViewModel(CargarCarteleraUseCase(moviesRepository))
   MainScreen(peliListViewModel) {}
}
package com.senkou.carteleraa7.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senkou.carteleraa7.ui.common.NoResult
import com.senkou.domain.model.Pelicula

@Composable
fun MovieList(
   movies: List<Pelicula>,
   onMovieClicked: (movieId: Int) -> Unit,
) {

   if (movies.isNotEmpty()) {
      val state = rememberLazyListState()

      LazyColumn(
         modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
         state = state,
         contentPadding = PaddingValues(24.dp),
         verticalArrangement = Arrangement.spacedBy(24.dp)
      ) {

         items(
            items = movies,
            key = { it.idEspectaculo },
            itemContent = { movie ->
               MovieItem(movie = movie) { movieId ->
                  onMovieClicked(movieId)
               }
            }
         )
      }
   } else {
      NoResult()
   }
}
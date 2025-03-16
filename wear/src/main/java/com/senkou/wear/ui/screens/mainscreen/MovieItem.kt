package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.compose.material3.Card
import androidx.wear.tooling.preview.devices.WearDevices
import coil.compose.AsyncImage
import com.senkou.domain.common.format
import com.senkou.domain.model.Pelicula
import com.senkou.wear.ui.theme.CarteleraA7Theme
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.fondoFechaEstreno
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieItem(
   movie: Pelicula,
   onMovieClicked: (movieId: Int) -> Unit,
) {
   Card(
      onClick = { onMovieClicked(movie.idEspectaculo) },
      modifier = Modifier.padding(8.dp).fillMaxWidth().aspectRatio(2 / 3f),
      border = BorderStroke(2.dp, Color.White),
      contentPadding = PaddingValues(0.dp)
   ) {
      Box {
         AsyncImage(
            model = movie.cartel,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = movie.titulo
         )

         if (movie.fechaEstreno.isNotEmpty()) {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = parser.parse(parser.format(Date()))
            val releaseDate = parser.parse(movie.fechaEstreno)
            if (releaseDate != null && date != null) {
               if (releaseDate >= date) {
                  Text(
                     modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(15.dp, 15.dp))
                        .background(fondoFechaEstreno),
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     style = Typography.body1,
                     text = releaseDate.format()
                  )
               }
            }
         }
      }
   }
}

@Preview(device = WearDevices.LARGE_ROUND)
@Composable
fun MovieItemPreview() {
   CarteleraA7Theme {
      MovieItem(
         movie = Pelicula(
            "https://images.pexels.com/photos/1758144/pexels-photo-1758144.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "01-01-0001",
            0,
            "Título",
            "TítuloOriginal",
         )
      ) { }
   }
}

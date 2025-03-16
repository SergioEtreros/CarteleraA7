package com.senkou.carteleraa7.ui.mainscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.carteleraa7.ui.theme.Typography
import com.senkou.carteleraa7.ui.theme.releaseDateBackground
import com.senkou.domain.common.format
import com.senkou.domain.model.Pelicula
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieItem(
   movie: Pelicula,
   onMovieClicked: (movieId: Int) -> Unit,
) {
   Card(
      onClick = { onMovieClicked(movie.idEspectaculo) },
      modifier = Modifier
         .padding(8.dp)
         .fillMaxWidth()
         .aspectRatio(2 / 3f),
      border = BorderStroke(2.dp, Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
   ) {
      Box {
         AsyncImage(
            model = movie.cartel,
            contentScale = ContentScale.Crop,
            modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
            alignment = Alignment.TopCenter,
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
                        .background(releaseDateBackground),
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     style = Typography.bodyMedium,
                     text = releaseDate.format()
                  )
               }
            }
         }
      }
   }
}

@Preview(showSystemUi = false)
@Composable
fun MovieItemPreview() {
   MovieItem(
      movie = Pelicula(
         "",
         "01-01-0001",
         1,
         "Peli",
         "PeliOriginal",
      )
   ) { }
}



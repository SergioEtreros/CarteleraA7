package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Glow
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.senkou.domain.common.format
import com.senkou.domain.model.Pelicula
import com.senkou.tv.ui.theme.Typography
import com.senkou.tv.ui.theme.releaseDateBackground
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieItem(
   movie: Pelicula,
   onMovieClicked: (movieId: Int) -> Unit,
   onFocus: (movie: Pelicula) -> Unit
) {
   Card(
      onClick = { onMovieClicked(movie.idEspectaculo) },
      modifier = Modifier
         .width(120.dp)
         .aspectRatio(2 / 3f)
         .onFocusChanged {
            if (it.isFocused) {
               onFocus(movie)
            }
         },
      border = CardDefaults.border(
         focusedBorder = Border(BorderStroke(2.dp, Color.White)),
      ),
      glow = CardDefaults.glow(
         glow = Glow(Color.Black, 8.dp),
         focusedGlow = Glow(Color.White, 8.dp)
      )
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
                        .background(releaseDateBackground)
                        .padding(vertical = 4.dp),
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     style = Typography.bodyMedium,
                     fontWeight = FontWeight.Bold,
                     text = releaseDate.format()
                  )
               }
            }
         }
      }
   }
}




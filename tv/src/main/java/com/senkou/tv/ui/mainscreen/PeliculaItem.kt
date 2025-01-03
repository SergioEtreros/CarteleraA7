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
import com.senkou.tv.ui.theme.fondoFechaEstreno
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PeliculaItem(
   pelicula: Pelicula,
   onMovieClicked: (idEspectaculo: Int, background: String) -> Unit,
   onFocus: (Pelicula) -> Unit
) {
   Card(
      onClick = { onMovieClicked(pelicula.idEspectaculo, pelicula.background) },
      modifier = Modifier
         .width(120.dp)
         .aspectRatio(2 / 3f)
         .onFocusChanged {
            if (it.isFocused) {
               onFocus(pelicula)
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
            model = pelicula.cartel,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = pelicula.titulo
         )

         if (pelicula.fechaEstreno.isNotEmpty()) {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fecha = parser.parse(parser.format(Date()))
            val fechaEstreno = parser.parse(pelicula.fechaEstreno)
            if (fechaEstreno != null && fecha != null) {
               if (fechaEstreno >= fecha) {
                  Text(
                     modifier = Modifier
                        .fillMaxWidth()
                        .background(fondoFechaEstreno)
                        .padding(vertical = 4.dp),
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     style = Typography.bodyMedium,
                     fontWeight = FontWeight.Bold,
                     text = fechaEstreno.format()
                  )
               }
            }
         }
      }
   }
}




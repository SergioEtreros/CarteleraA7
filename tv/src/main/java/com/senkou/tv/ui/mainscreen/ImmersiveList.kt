package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.senkou.domain.common.parseDate
import com.senkou.domain.model.Pelicula
import com.senkou.tv.ui.common.AlphaBackground
import java.net.URLEncoder

@Composable
fun ImmersiveList(
   modifier: Modifier = Modifier,
   background: String,
   peliculas: List<Pelicula>,
   onMovieClicked: (idEspectaculo: Int, background: String) -> Unit,
   onMove: (Pelicula) -> Unit
) {

   var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

   Box(modifier = modifier) {

      AlphaBackground(background)

      Box (Modifier.align(Alignment.TopStart).padding(start = 24.dp, top = 12.dp)) {
         Column {

            Text(
               text = peliculas[selectedIndex].titulo,
               color = Color.White,
               fontWeight = FontWeight.Bold,
               style = MaterialTheme.typography.displaySmall.copy(
                  shadow = Shadow(
                     color = Color.Black,
                     offset = Offset(8f, 8f),
                     blurRadius = 4f
                  )
               )
            )

            Text(
               text = peliculas[selectedIndex].fechaEstreno.parseDate(),
               Modifier.padding(top = 4.dp),
               color = Color.White,
               fontWeight = FontWeight.Bold,
            )
         }
      }


      Box (Modifier.align(Alignment.BottomEnd)) {

         CartelList(
            lista = peliculas,
            onMovieClicked = { idEspectaculo ->
               onMovieClicked(
                  idEspectaculo,
                  URLEncoder.encode(background, "UTF-8")
               )
            },
            onFocus = { peli ->
               onMove(peli)
               selectedIndex = peliculas.indexOf(peli)
            }
         )
      }
   }
}
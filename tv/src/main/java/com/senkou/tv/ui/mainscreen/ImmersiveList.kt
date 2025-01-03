package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun ImmersiveList(
   modifier: Modifier = Modifier,
   peliculas: List<Pelicula>,
   onMovieClicked: (idEspectaculo: Int, background: String) -> Unit,
) {

   var selectedItem by remember { mutableStateOf(peliculas.firstOrNull()) }

   Box(modifier = modifier) {

      AlphaBackground(selectedItem?.background)

      Box(
         Modifier
            .align(Alignment.TopStart)
            .padding(start = 24.dp, top = 12.dp)
      ) {
         Column {

            Text(
               text = selectedItem?.titulo ?: "",
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
               text = selectedItem?.fechaEstreno?.parseDate() ?: "",
               Modifier.padding(top = 4.dp),
               color = Color.White,
               fontWeight = FontWeight.Bold,
            )
         }
      }

      Box(Modifier.align(Alignment.BottomEnd)) {
         CartelList(
            lista = peliculas,
            onMovieClicked = { idEspectaculo, background ->
               onMovieClicked(
                  idEspectaculo,
                  background
               )
            },
            onFocus = { peli ->
               selectedItem = peli
            }
         )
      }
   }
}

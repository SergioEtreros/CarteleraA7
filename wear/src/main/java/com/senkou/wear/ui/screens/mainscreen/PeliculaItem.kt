package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import coil.compose.AsyncImage
import com.senkou.domain.common.format
import com.senkou.domain.model.Pelicula
import com.senkou.wear.ui.theme.CarteleraA7Theme
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.fondoFechaEstreno
import com.senkou.wear.ui.theme.fondoLogo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PeliculaItem(
   pelicula: Pelicula,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {
   val shape = RoundedCornerShape(15.dp)

   Box(modifier = Modifier
      .padding(8.dp)
      .shadow(35.dp, shape, spotColor = Color.Black)
      .clip(shape)
      .border(2.dp, fondoLogo, shape)
      .clickable {
         onMovieClicked(pelicula.idEspectaculo)
      }) {
      AsyncImage(
         model = pelicula.cartel,
         contentScale = ContentScale.Fit,
         modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
         alignment = Alignment.TopCenter,
//         loading = { CircularProgressIndicator() },
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
                     .wrapContentHeight()
                     .clip(RoundedCornerShape(15.dp, 15.dp))
                     .background(fondoFechaEstreno),
                  textAlign = TextAlign.Center,
                  color = Color.White,
                  style = Typography.body1,
                  text = fechaEstreno.format()
               )
            }
         }
      }
   }
}

@Preview(device = WearDevices.LARGE_ROUND)
@Composable
fun PeliculaItemPreview() {
   CarteleraA7Theme {
      PeliculaItem(
         pelicula = Pelicula(
            "https://images.pexels.com/photos/1758144/pexels-photo-1758144.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "01-01-0001",
            0,
            "Título",
            "TítuloOriginal",
         )
      ) { }
   }
}

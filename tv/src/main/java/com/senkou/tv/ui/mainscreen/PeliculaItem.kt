package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.senkou.domain.common.format
import com.senkou.domain.model.Pelicula
import com.senkou.tv.ui.theme.Typography
import com.senkou.tv.ui.theme.fondoFechaEstreno
import com.senkou.tv.ui.theme.fondoLogo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PeliculaItem(
   pelicula: Pelicula,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   val shape = RoundedCornerShape(15.dp)

   Box(modifier = Modifier
      .height(400.dp)
      .width(290.dp)
      .shadow(8.dp, shape, spotColor = Color.Black)
      .clip(shape)
      .border(2.dp, fondoLogo, shape)
      .clickable {
         onMovieClicked(pelicula.iDEspectaculo)
      }) {
      AsyncImage(
         model = pelicula.cartel,
         contentScale = ContentScale.Crop,
         modifier = Modifier.fillMaxSize(),
//         alignment = Alignment.TopCenter,
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
                     .background(fondoFechaEstreno)
                     .padding(8.dp),
                  textAlign = TextAlign.Center,
                  color = Color.White,
                  style = Typography.bodyLarge,
                  fontWeight = FontWeight.Bold,
                  text = fechaEstreno.format()
               )
            }
         }
      }
   }
}

@Preview(showSystemUi = false)
@Composable
fun PeliculaItemPreview() {
   PeliculaItem(
      pelicula = Pelicula(
         "",
         "01-01-0001",
         1,
         "Peli",
      )
   ) { }
}



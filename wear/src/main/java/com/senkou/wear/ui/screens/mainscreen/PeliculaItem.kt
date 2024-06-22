package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import coil.compose.SubcomposeAsyncImage
import com.senkou.domain.common.format
import com.senkou.domain.model.Pelicula
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.color_blanco
import com.senkou.wear.ui.theme.fondoFechaEstreno
import com.senkou.wear.ui.theme.resalte_ticket
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PeliculaItem(
   pelicula: Pelicula,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   Card(modifier = Modifier
      .padding(10.dp, 3.dp) // Si está antes del tamaño/background, es margin sino, padding
      .fillMaxWidth(),
      onClick = { onMovieClicked(pelicula.iDEspectaculo) }
   ) {

      Column(modifier = Modifier.fillMaxSize()) {
         Text(
            modifier = Modifier
               .fillMaxWidth()
               .background(
                  color = resalte_ticket,
                  shape = RoundedCornerShape(5.dp)
               )
               .padding(2.dp),
//                overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = Typography.body1,
            text = pelicula.titulo
         )

//            MarqueeText(modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    color = colorResource(R.color.resalte_ticket),
//                    shape = RoundedCornerShape(5.dp))
//                .padding(2.dp)
//                ,
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                style = Typography.body1,
//                text = data.titulo)

         Spacer(modifier = Modifier.height(2.dp))

         Scaffold {
            SubcomposeAsyncImage(
               model = pelicula.cartel,
               contentScale = ContentScale.Crop,
               modifier = Modifier
                  .fillMaxWidth()
                  .height(185.dp)
                  .clip(RoundedCornerShape(15.dp)),
               alignment = Alignment.TopCenter,
               loading = { CircularProgressIndicator() },
               contentDescription = pelicula.titulo
            )

            if (pelicula.fechaEstreno.isNotEmpty()) {
//                    val parser = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
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
                        color = color_blanco,
                        style = Typography.body2,
                        text = fechaEstreno.format()
                     )
                  }
               }
            }
         }
      }
   }
}
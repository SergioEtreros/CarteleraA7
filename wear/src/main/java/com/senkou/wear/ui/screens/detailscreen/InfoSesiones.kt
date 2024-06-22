package com.senkou.wear.ui.screens.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.senkou.domain.common.crearTextoSesiones
import com.senkou.domain.model.Sesion
import com.senkou.wear.ui.common.RoundedCutoutShape
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.color_blanco

@Composable
fun InfoSesiones(urlImagen: String, fecha: String, sesiones: List<Sesion>) {

   val shape = RoundedCutoutShape(offset = 100.0F, 4.dp, Orientation.Horizontal)

   Row(
      modifier = Modifier
         .width(150.dp)
         .height(70.dp)
         .background(
            color = color_blanco,
            shape = shape
         )
         .padding(6.dp, 3.dp)
   ) {
      AsyncImage(
         model = urlImagen,
         contentScale = ContentScale.Fit,
         modifier = Modifier
            .width(40.dp)
            .fillMaxHeight()
            .align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(5.dp)),
         contentDescription = "",
//         loading = { CircularProgressIndicator() }
      )

      Spacer(modifier = Modifier.width(8.dp))

      Column(modifier = Modifier.fillMaxWidth()) {

         Text(
            modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = Typography.display3,
            fontWeight = FontWeight.Bold,
            text = fecha
         )

         Spacer(modifier = Modifier.width(3.dp))

         Text(
            modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = Typography.display3,
            text = sesiones.crearTextoSesiones()
         )
      }
   }
}

@Preview
@Composable
fun PreviewInfoSesiones() {
   InfoSesiones(
      urlImagen = "https://images.pexels.com/photos/1758144/pexels-photo-1758144.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
      fecha = "09/03/2023",
      sesiones = emptyList()
   )
}
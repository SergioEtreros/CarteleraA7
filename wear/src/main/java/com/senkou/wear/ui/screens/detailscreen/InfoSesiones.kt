package com.senkou.wear.ui.screens.detailscreen

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import coil.compose.SubcomposeAsyncImage
import com.senkou.domain.common.crearTextoSesiones
import com.senkou.domain.model.Sesion
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.color_blanco

@Composable
fun InfoSesiones(urlImagen: String, fecha: String, sesiones: List<Sesion>) {
   Row(
      modifier = Modifier
         .width(150.dp)
         .height(70.dp)
         .background(
            color = color_blanco,
            shape = RoundedCornerShape(7.dp)
         )
         .padding(6.dp, 3.dp)
   ) {
      SubcomposeAsyncImage(
         model = urlImagen,
         contentScale = ContentScale.Inside,
         modifier = Modifier
            .width(40.dp)
            .fillMaxHeight()
            .align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(5.dp)),
         contentDescription = "",
         loading = { CircularProgressIndicator() }
      )

      Spacer(modifier = Modifier.width(6.dp))

      Column(modifier = Modifier.fillMaxWidth()) {

         Text(
            modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = Typography.body1,
            text = fecha
         )

         Spacer(modifier = Modifier.width(3.dp))

         Text(
            modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = Typography.display2,
            text = sesiones.crearTextoSesiones()
         )
      }
   }
}

@Preview
@Composable
fun PreviewInfoSesiones() {
//    InfoSesiones("", "09/03/2023", "sdfsdfsfsfd")
}
package com.senkou.carteleraa7.ui.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.carteleraa7.ui.common.RoundedCutoutShape
import com.senkou.carteleraa7.ui.theme.Typography
import com.senkou.carteleraa7.ui.theme.fondoLogo
import com.senkou.domain.common.crearTextoSesiones
import com.senkou.domain.model.Sesion

@Composable
fun InfoSesiones(urlImagen: String, fecha: String, sesiones: List<Sesion>) {

   val shape = RoundedCutoutShape(offset = 287.0F, 10.dp, Orientation.Horizontal)

   Row(
      modifier = Modifier
         .width(250.dp)
         .height(140.dp)
         .background(
            color = Color.White,
            shape = shape
         )
         .border(1.dp, fondoLogo, shape)
         .padding(12.dp, 12.dp),
   ) {
      AsyncImage(
         model = urlImagen,
         contentScale = ContentScale.Fit,
         modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(5.dp)),
         contentDescription = "",
//         loading = { CircularProgressIndicator() }
      )

      Spacer(modifier = Modifier.width(12.dp))

      val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
      Canvas(
         Modifier
            .fillMaxHeight()
            .width(2.dp)
      ) {

         drawLine(
            color = Color.Gray,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            pathEffect = pathEffect
         )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.fillMaxWidth()) {

         Text(
            modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = Typography.bodyMedium,
            text = fecha
         )

         Spacer(modifier = Modifier.width(3.dp))

         Text(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = Typography.bodySmall,
            text = sesiones.crearTextoSesiones()
         )
      }
   }
}

@Preview
@Composable
fun PreviewInfoSesiones() {
   InfoSesiones("", "09/03/2023", emptyList())
}
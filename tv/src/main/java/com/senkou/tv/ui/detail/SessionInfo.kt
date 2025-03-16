package com.senkou.tv.ui.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Glow
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.senkou.domain.common.crearTextoSesiones
import com.senkou.domain.model.Sesion
import com.senkou.tv.ui.common.RoundedCutoutShape
import com.senkou.tv.ui.theme.Typography
import com.senkou.tv.ui.theme.logoBackground

@Composable
fun SessionInfo(imageUrl: String, date: String, sessiones: List<Sesion>) {

   val shape = RoundedCutoutShape(offset = 210.0F, 10.dp, Orientation.Horizontal)

   Card(
      onClick = { },
      shape = CardDefaults.shape(shape),
      glow = CardDefaults.glow(focusedGlow = Glow(Color.White, 8.dp)),
      border = CardDefaults.border(focusedBorder = Border.None),
   ) {

      Row(
         modifier = Modifier
            .width(250.dp)
            .height(140.dp)
            .background(
               color = Color.White,
               shape = shape
            )
            .border(1.dp, logoBackground, shape)
            .padding(12.dp, 12.dp),
      ) {
         AsyncImage(
            model = imageUrl,
            contentScale = ContentScale.Fit,
            modifier = Modifier
               .width(80.dp)
               .fillMaxHeight()
               .align(Alignment.CenterVertically)
               .clip(RoundedCornerShape(5.dp)),
            contentDescription = "",
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
               text = date
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
               modifier = Modifier
                  .fillMaxSize()
                  .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
               color = Color.Black,
               textAlign = TextAlign.Start,
               style = Typography.bodySmall,
               text = sessiones.crearTextoSesiones()
            )
         }
      }
   }
}

@Preview
@Composable
fun PreviewSessionInfo() {
   SessionInfo("", "09/03/2023", emptyList())
}
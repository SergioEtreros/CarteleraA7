package com.senkou.tv.ui.common

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.net.URLDecoder


@Composable
fun AlphaBackground(background: String?) {

   val height = 460.dp
   
   BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
      if (!background.isNullOrBlank()) {
         AsyncImage(
            model = URLDecoder.decode(background, "UTF-8"),
            contentScale = ContentScale.Crop,
            modifier = Modifier
               .height(height)
               .align(Alignment.TopEnd)
               .aspectRatio(16f / 9),
            contentDescription = ""
         )
      }

      Box(
         modifier = Modifier
            .height(height)
            .align(Alignment.TopEnd)
            .aspectRatio(16f / 9)
            .scale(2f, 1.3f)
            .background(
               Brush.radialGradient(
                  colors = listOf(Color.Transparent, Color.Black),
                  center = Offset(maxWidth.toPx() / 2.025f, height.toPx() / 2.6f)
               )
            )
      ) { }
   }
 }

@Preview(widthDp = 900, heightDp = 640)
@Composable
fun AlphaBackgroundPreview() {
   AlphaBackground("https://image.tmdb.org/t/p/original/tElnmtQ6yz1PjN1kePNl8yMSb59.jpg")
}

fun Dp.toPx(): Float = value * Resources.getSystem().displayMetrics.density

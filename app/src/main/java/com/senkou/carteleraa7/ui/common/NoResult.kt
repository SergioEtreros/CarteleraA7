package com.senkou.carteleraa7.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight

@Composable
fun NoResult() {
   Box(
      modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
      contentAlignment = Alignment.Center
   ) {
      Text(
         text = "No se han econtrado películas",
         color = Color.White,
         fontWeight = FontWeight.Bold,
         style = MaterialTheme.typography.headlineMedium.copy(
            shadow = Shadow(
               color = Color.Black,
               offset = Offset(8f, 8f),
               blurRadius = 4f
            )
         )
      )
   }
}
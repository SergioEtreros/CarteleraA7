package com.senkou.tv.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@Composable
fun NoResult() {
   Box(
      modifier = Modifier.fillMaxSize().padding(start = 24.dp),
      contentAlignment = Alignment.Center
   ) {
      Text(
         text = "No se han econtrado películas",
         color = Color.White,
         fontWeight = FontWeight.Bold,
         style = MaterialTheme.typography.displayMedium.copy(
            shadow = Shadow(
               color = Color.Black,
               offset = Offset(8f, 8f),
               blurRadius = 4f
            )
         )
      )
   }
}
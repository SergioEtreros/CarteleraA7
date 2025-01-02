package com.senkou.tv.ui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import com.senkou.tv.ui.theme.CarteleraA7Theme

@Composable
fun Screen(content: @Composable BoxScope.() -> Unit) {
   CarteleraA7Theme {
      Surface(
         modifier = Modifier.fillMaxSize(),
         colors = SurfaceDefaults.colors(containerColor = Color.Black),
         content = content
      )
   }
}
package com.senkou.carteleraa7.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senkou.carteleraa7.ui.theme.CarteleraA7Theme

@Composable
fun Screen(content: @Composable () -> Unit) {
   CarteleraA7Theme {
      // A surface container using the 'background' color from the theme
      Surface(
         modifier = Modifier.fillMaxSize(),
         color = MaterialTheme.colorScheme.surface,
         content = content
      )
   }
}
package com.senkou.carteleraa7.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val colorScheme = darkColorScheme(
   primary = fondoFechaEstreno,
   secondary = resalte_ticket,
   surface = resalte_ticket,
   surfaceVariant = fondoLogo,
   background = fondo_lista
)

@Composable
fun CarteleraA7Theme(
   darkTheme: Boolean = isSystemInDarkTheme(),
   dynamicColor: Boolean = true,
   content: @Composable () -> Unit
) {
   MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
   )
}
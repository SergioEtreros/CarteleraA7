package com.senkou.tv.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senkou.tv.R
import com.senkou.tv.ui.Screen
import com.senkou.tv.ui.mainscreen.PeliListViewModel
import com.senkou.tv.ui.theme.fondoLogo

@Composable

fun SplashScreen(
   model: PeliListViewModel,
   onMoviesLoaded: () -> Unit,
) {
   val state by model.state.collectAsStateWithLifecycle()

   if (state.peliculas.isNotEmpty()) {
      onMoviesLoaded()
   }

   Splash()
}

@Composable
fun Splash() {

   Screen {
      Column(
         modifier = Modifier
            .fillMaxSize()
            .background(fondoLogo),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
      ) {
         Image(
            painter = painterResource(
               id = R.drawable.ic_launcher_foreground
            ),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Splash"
         )
      }
   }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
   Splash()
}
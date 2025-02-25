package com.senkou.wear.ui.screens.splashscreen

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
import com.senkou.wear.R
import com.senkou.wear.ui.common.ifSuccess
import com.senkou.wear.ui.screens.mainscreen.PeliViewModel
import com.senkou.wear.ui.theme.fondoLogo

@Composable
fun SplashScreen(
   model: PeliViewModel,
   onMoviesLoaded: () -> Unit,
) {
   val state by model.state.collectAsStateWithLifecycle()

//   LaunchedEffect(state.peliculas.isNotEmpty()) {
//      delay(250)
//      onMoviesLoaded()
//   }

   state.ifSuccess { onMoviesLoaded() }

   Splash()
}

@Composable
fun Splash() {

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
         contentDescription = "Splash"
      )
   }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
   Splash()
}
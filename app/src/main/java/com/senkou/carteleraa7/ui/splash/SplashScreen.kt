package com.senkou.carteleraa7.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.ui.Screen
import com.senkou.carteleraa7.ui.common.ifSuccess
import com.senkou.carteleraa7.ui.mainscreen.MovieListViewModel

@Composable
fun SplashScreen(
   model: MovieListViewModel,
   onMoviesLoaded: () -> Unit,
) {

   val state by model.state.collectAsStateWithLifecycle()

   state.ifSuccess {
      if (it.movies.isNotEmpty()) onMoviesLoaded()
   }

   Splash()
}

@Composable
fun Splash() {

   Screen {
      Column(
         modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
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
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
   Splash()
}
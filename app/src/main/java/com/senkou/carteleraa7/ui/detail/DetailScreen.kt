package com.senkou.carteleraa7.ui.detail

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senkou.carteleraa7.ui.Screen
import com.senkou.carteleraa7.ui.detail.DetailViewModel.UiState

@Composable
fun DetailScreen(
   model: DetailViewModel,
   onBack: () -> Unit
) {
   val state by model.uiState.collectAsStateWithLifecycle(UiState())

   DetailScreen(
      state = state,
      onBack = onBack,
      onPlayTrailer = { video -> model.playTrailer(video) }
   )
}

@Composable
fun DetailScreen(
   state: UiState,
   onBack: () -> Unit,
   onPlayTrailer: (video: String) -> Unit
) {
   Screen {
      Scaffold(
         topBar = {
            DetailTopBar(state, onBack)
         },
         contentWindowInsets = WindowInsets.safeDrawing
      ) { padding ->
         Details(state, padding, onPlayTrailer)
      }
   }
}


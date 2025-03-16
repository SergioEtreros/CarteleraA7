package com.senkou.carteleraa7.ui.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.senkou.carteleraa7.ui.theme.releaseDateBackground

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailTopBar(
   state: DetailViewModel.UiState,
   onBack: () -> Unit
) {
   TopAppBar(
      title = {
         Text(
            text = state.sessions.firstOrNull()?.titulo ?: "",
            color = MaterialTheme.colorScheme.onSurface
         )
      },
      navigationIcon = {
         IconButton(onClick = onBack) {
            Icon(
               imageVector = Icons.AutoMirrored.Default.ArrowBack,
               contentDescription = null
            )
         }
      },
      colors = TopAppBarDefaults.topAppBarColors(
         containerColor = releaseDateBackground,
      )
   )
}
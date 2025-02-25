package com.senkou.wear.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator

const val LOADING_INDICATOR_TEST_TAG = "LoadingIndicator"

@Composable
fun LoadingIndicator(paddingValues: PaddingValues = PaddingValues(0.dp)) {
   Box(
      modifier = Modifier
         .fillMaxSize()
         .padding(paddingValues)
         .testTag(LOADING_INDICATOR_TEST_TAG),
      contentAlignment = Alignment.Center
   ) {
      CircularProgressIndicator()
   }
}
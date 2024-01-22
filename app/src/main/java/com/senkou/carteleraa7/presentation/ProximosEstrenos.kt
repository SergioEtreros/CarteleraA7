package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.senkou.carteleraa7.presentation.theme.fondo_lista

@Composable
fun ProximosEstrenos(navController: NavHostController, model: PeliViewModel = viewModel()) {

   val lista = model.proximosEstrenos.value

   if (lista != null) {
      val state = rememberLazyListState()

      LazyColumn(
         modifier = Modifier.background(fondo_lista),
         state = state,
         contentPadding = PaddingValues(24.dp),
         verticalArrangement = Arrangement.spacedBy(24.dp)
      ) {
         itemsIndexed(
            items = lista,
            itemContent = { index, peli ->
               PeliculaItem(navController, data = peli, index)
            }
         )
      }
   }
}
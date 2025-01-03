package com.senkou.tv.ui.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.rememberDrawerState
import com.senkou.domain.model.Pelicula
import com.senkou.tv.ui.Screen
import com.senkou.tv.ui.common.NoResult

@Composable
fun MainScreen(
   model: PeliListViewModel,
   onMovieClicked: (idEspectaculo: Int, background: String) -> Unit,
) {
   val state by model.state.collectAsStateWithLifecycle()

   MainScreen(state, onMovieClicked)
}

@Composable
fun MainScreen(
   state: PeliListViewModel.UiState,
   onMovieClicked: (idEspectaculo: Int, background: String) -> Unit,
) {
   Screen {
      var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
      val closeDrawerWidth = 80.dp
      val drawerState = rememberDrawerState(DrawerValue.Closed)

      ModalNavigationDrawer(
         drawerContent = {
            Column(
               Modifier
                  .fillMaxHeight()
                  .padding(12.dp)
                  .selectableGroup(),
               horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.Center
            ) {
               DrawerMenuItems(selectedIndex) { index ->
                  selectedIndex = index
                  drawerState.setValue(DrawerValue.Closed)
               }
            }
         },
         drawerState = drawerState,
         scrimBrush = Brush.horizontalGradient(listOf(Color.Black, Color.Transparent))
      ) {
         when (selectedIndex) {
            0 -> {
               ImmersiveList(
                  modifier = Modifier.padding(start = closeDrawerWidth),
                  peliculas = state.peliculas,
                  onMovieClicked = onMovieClicked,
               )
            }

            1 -> {
               if (state.proximosEstrenos.isNotEmpty()) {
                  ImmersiveList(
                     modifier = Modifier.padding(start = closeDrawerWidth),
                     peliculas = state.proximosEstrenos,
                     onMovieClicked = { _, _ -> },
                  )
               } else {
                  NoResult()
               }
            }
         }
      }
   }
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {

   MainScreen(PeliListViewModel.UiState(listOf(Pelicula(
      cartel = "cartel",
      fechaEstreno = "fechaEstreno",
      idEspectaculo = 1,
      titulo = "titulo",
      tituloOriginal = "tituloOriginal",
      background = "background",
   )), listOf())) { _, _ -> }
}
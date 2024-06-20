package com.senkou.wear.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.items
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.senkou.wear.data.DataA7
import com.senkou.wear.ui.theme.fondo_lista
import com.senkou.wear.repository.RepoWeb


@Composable
fun MainScreen(navController: NavHostController, model: PeliViewModel) {

   val lista = model.getPeliculas()

   if (lista != null) {
      ScalingLazyColumn(
         modifier = Modifier
            .background(fondo_lista),
//            contentPadding = PaddingValues(16.dp)
      ) {

         items(
            items = lista,
            itemContent = { peli ->
               PeliculaItem(navController, data = peli, lista.indexOf(peli))
            }
         )
      }
   }
}

@Preview
@Composable
fun DefaultPreview() {
   val model = PeliViewModel()
   model.dataA7 = DataA7(RepoWeb())
   model.cargarCartelera()
   MainScreen(rememberSwipeDismissableNavController(), model)
}
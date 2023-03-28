package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.senkou.carteleraa7.presentation.theme.fondo_lista

@Composable
fun MainScreen(navController: NavHostController, model: PeliViewModel = viewModel()) {

    val lista = model.peliculas.value

    if (lista != null) {

//        val state = rememberScrollState()

        LazyColumn(
            modifier = Modifier.background(fondo_lista),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            itemsIndexed(
                items = lista,
                itemContent = {index, peli ->
                    PeliculaItem(navController, data = peli, index)
                }
            )
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {
    val model: PeliViewModel = viewModel()
//    model.dataA7 = DataA7(RepoWeb())
    model.cargarCartelera()
    MainScreen(rememberNavController(), model)
}
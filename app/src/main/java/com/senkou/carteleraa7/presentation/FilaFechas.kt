package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senkou.carteleraa7.data.model.Peli

@Composable
fun FilaFechas(data: Peli, model: PeliViewModel = viewModel()){

    val lista = model.obtenerDiasPeli(data)

    lista?.let {

        LazyRow(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(
                items = lista,
                itemContent = {dia ->
                    val fecha = if (dia == "Hoy") {""} else {dia}
                    InfoSesiones(data.urlImagen, dia, data.crearTextoSesiones(fecha))
                }
            )
        }
    }

}
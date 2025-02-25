package com.senkou.wear.ui.screens.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.senkou.domain.common.crearDetalles
import com.senkou.wear.ui.common.LoadingIndicator
import com.senkou.wear.ui.common.Result
import com.senkou.wear.ui.screens.mainscreen.MarqueeText
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.fondo_lista
import com.senkou.wear.ui.theme.resalte_ticket

@Composable
fun DetallePelicula(model: DetalleViewModel) {
   val state by model.uiState.collectAsStateWithLifecycle()

   DetallePelicula(state = state)
}

@Composable
fun DetallePelicula(state: Result<DetalleViewModel.UiState>) {
   Scaffold {
      when (state) {
         Result.Loading -> LoadingIndicator()
         is Result.Error -> Text(text = state.throwable.message.orEmpty())
         is Result.Success -> {
            if (state.data.sesiones.isNotEmpty()) {
               Column(
                  modifier = Modifier
                     .fillMaxSize()
                     .background(fondo_lista),
                  horizontalAlignment = Alignment.CenterHorizontally
               ) {
                  Box(
                     modifier = Modifier
                        .fillMaxWidth()
                        .background(
                           color = resalte_ticket,
                           shape = RoundedCornerShape(5.dp)
                        ),
                     contentAlignment = Alignment.Center
                  ) {

                     MarqueeText(
                        modifier = Modifier
                           .padding(horizontal = 48.dp, vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        style = Typography.body2,
                        text = state.data.sesiones.first().titulo,
                        gradientEdgeColor = Color.Transparent
                     )
                  }

                  Column(
                     Modifier
                        .padding(16.dp, 6.dp)
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState(), true)
                  ) {

                     FilaFechas(state.data.sesiones, state.data.diasSesiones)

                     state.data.sesiones.first().crearDetalles().forEach { detalles ->

                        Text(
                           modifier = Modifier
                              .fillMaxWidth()
                              .wrapContentHeight(),
                           color = Color.White,
                           textAlign = TextAlign.Justify,
                           style = Typography.display2,
                           text = detalles
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                     }

                     Spacer(modifier = Modifier.height(28.dp))
                  }
               }
            }
         }
      }


   }
}

//@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
//@Composable
//fun DetallePeliculaPreview() {
//
//   val moviesRepository = MoviesRepository(WebMovieDatasource())
//
//   CarteleraA7Theme {
//      DetallePelicula(
//         DetalleViewModel(
//            0,
//            cargarDetalle = CargarDetalleUseCase(moviesRepository),
//         )
//      )
//   }
//}
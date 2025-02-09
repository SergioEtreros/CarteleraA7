package com.senkou.carteleraa7.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.senkou.carteleraa7.ui.Screen
import com.senkou.carteleraa7.ui.theme.Typography
import com.senkou.carteleraa7.ui.theme.color_blanco
import com.senkou.carteleraa7.ui.theme.fondoFechaEstreno
import com.senkou.carteleraa7.ui.theme.fondo_lista
import com.senkou.carteleraa7.ui.theme.transparente
import com.senkou.domain.common.crearDetalles

@Composable
fun DetallePelicula(
   model: DetalleViewModel,
   onBack: () -> Unit
) {

   Screen {
      Scaffold(
         contentWindowInsets = WindowInsets.safeDrawing
      ) { padding ->

         val state by model.uiState.collectAsStateWithLifecycle()

         if (state.sesiones.isNotEmpty()) {
            Column(
               modifier = Modifier
                  .fillMaxSize()
                  .padding(padding)
                  .background(fondo_lista)
                  .verticalScroll(state = rememberScrollState(), true),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {

               Box {
                  AsyncImage(
                     model = state.sesiones.first().cartel,
                     contentScale = ContentScale.Crop,
                     modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                     contentDescription = "",
                  )

                  Text(
                     modifier = Modifier
                        .fillMaxWidth()
                        .background(
                           color = fondoFechaEstreno,
                        )
                        .padding(16.dp, 8.dp, 16.dp, 8.dp),
                     maxLines = 1,
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     style = Typography.bodySmall,
                     text = state.sesiones.first().titulo
                  )

                  Button(modifier = Modifier
                     .padding(0.dp)
                     .align(Alignment.TopStart)
                     .offset(x = (-8).dp, y = (-8).dp),
                     elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
                     colors = ButtonDefaults.buttonColors(transparente),
                     contentPadding = PaddingValues(0.dp),
                     onClick = { onBack() }) {
                     Icon(
                        modifier = Modifier
                           .height(24.dp)
                           .width(24.dp),
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = color_blanco
                     )

                  }

                  FloatingActionButton(
                     modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.BottomEnd)
                        .offset(y = (25).dp),
                     onClick = {
                        model.playTrailer(state.sesiones.first().video)

                     },
                     containerColor = Color.Red,
                     contentColor = Color.White
                  ) {
                     Icon(Icons.Default.PlayArrow, "")
                  }
               }

               Spacer(modifier = Modifier.height(30.dp))

               Column(
                  Modifier
                     .padding(16.dp, 6.dp)
                     .wrapContentHeight()
               ) {

                  FilaFechas(state.sesiones, model.obtenerDiasSesiones())

                  state.sesiones.first().crearDetalles().forEach { detalles ->

                     Text(
                        modifier = Modifier
                           .fillMaxWidth()
                           .wrapContentHeight(),
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = Typography.labelSmall,
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
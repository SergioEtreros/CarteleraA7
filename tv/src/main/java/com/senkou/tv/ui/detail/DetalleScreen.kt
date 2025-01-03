package com.senkou.tv.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.senkou.domain.model.Sesion
import com.senkou.tv.ui.Screen
import com.senkou.tv.ui.common.AlphaBackground
import com.senkou.tv.ui.theme.Typography
import com.senkou.tv.ui.theme.fondoFechaEstreno
import com.senkou.tv.ui.theme.resalte_ticket
import java.net.URLDecoder

@Composable
fun DetallePelicula(
   model: DetalleViewModel,
) {
   val state by model.uiState.collectAsStateWithLifecycle()
   val diasSesiones = model.obtenerDiasSesiones()

   DetallePelicula(state = state, diasSesiones = diasSesiones) { url ->
      model.playTrailer(url)
   }
}

@Composable
fun DetallePelicula(
   state: DetalleViewModel.UiState,
   diasSesiones: List<String>,
   onPlayTrailer: (String) -> Unit = {}
) {
   Screen {
      val shape = RoundedCornerShape(8.dp)

      val (first, second, third, fourth) = remember { FocusRequester.createRefs() }

      AlphaBackground(background = URLDecoder.decode(state.background, "UTF-8"))

      if (state.sesiones.isNotEmpty()) {
         Box(
            modifier = Modifier
               .fillMaxSize()
               .padding(24.dp),
         ) {

            Row(
               modifier = Modifier
                  .fillMaxWidth()
            ) {
               AsyncImage(
                  model = state.sesiones.first().cartel,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                     .width(350.dp)
                     .fillMaxHeight()
                     .clip(shape)
                     .border(4.dp, resalte_ticket, shape),
                  contentDescription = "",
               )

               Spacer(modifier = Modifier.width(16.dp))

               Column(
                  modifier = Modifier
                     .fillMaxSize()
                     .verticalScroll(state = rememberScrollState(), true)
                     .focusRequester(first),
                  horizontalAlignment = Alignment.CenterHorizontally
               ) {

                  Text(
                     modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .background(color = fondoFechaEstreno)
                        .padding(16.dp, 8.dp, 16.dp, 8.dp),
                     maxLines = 1,
                     textAlign = TextAlign.Center,
                     color = Color.White,
                     style = Typography.bodyLarge,
                     fontWeight = FontWeight.Bold,
                     text = state.sesiones.first().titulo
                  )

                  Spacer(modifier = Modifier.height(16.dp))

                  Column {
                     FilaFechas(
                        sesiones = state.sesiones,
                        lista = diasSesiones,
                        modifier = Modifier.focusRequester(second)
                     )
                     Spacer(modifier = Modifier.height(16.dp))
                     Detalles(
                        sesion = state.sesiones.first(),
                        modifier = Modifier
                           .padding(end = 64.dp)
                           .focusRequester(third)
                     )
                  }
               }
            }
         }

         Button(
            modifier = Modifier
               .padding(end = 24.dp, bottom = 24.dp)
               .align(Alignment.BottomEnd)
               .focusRequester(fourth),
            onClick = { onPlayTrailer(state.sesiones.first().video) },
            colors = ButtonDefaults.colors(
               containerColor = Color.Red,
               contentColor = Color.White
            )
         ) {
            Icon(imageVector = Icons.Default.PlayArrow, "")
         }
      }
   }
}

@Preview
@Composable
fun DetallePeliculaPreview() {
   DetallePelicula(
      state = DetalleViewModel.UiState(
         sesiones = listOf(
            Sesion(
               duracion = "duracion",
               fechaEstrenoSpanish = "estreno",
               hora = "hora",
               iDEspectaculo = 1,
               iDPase = "idpase",
               iDSala = "iDSala",
               nombreSala = "nombreSala",
               nombreFormato = "nombreFormato",
               interpretes = "interpretes",
               nombreCalificacion = "nombreCalificacion",
               nombreGenero = "nombreGenero",
               sinopsis = "sinopsis",
               titulo = "titulo",
               tituloOriginal = "tituloOriginal",
               video = "video",
               cartel = "https://artesiete.es/Posters/sonic3.jpg",
               diacompleto = "Hoy",

               )
         ),
         background = "https://artesiete.es/Posters/sonic3.jpg"
      ),
      diasSesiones = listOf("Hoy")
   ) {}
}




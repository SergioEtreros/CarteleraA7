package com.senkou.tv.ui.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Glow
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

   DetallePelicula(state = state, diasSesiones = diasSesiones)
}

@Composable
fun DetallePelicula(
   state: DetalleViewModel.UiState,
   diasSesiones: List<String>,
) {
   Screen {
      val shape = RoundedCornerShape(8.dp)

      var trailerVisible by remember { mutableStateOf(false) }

      AlphaBackground(background = URLDecoder.decode(state.background, "UTF-8"))

      if (state.sesiones.isNotEmpty()) {
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .padding(24.dp),
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
                  .fillMaxSize(),
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

               FilaFechas(
                  sesiones = state.sesiones,
                  lista = diasSesiones,
               )

               Spacer(modifier = Modifier.height(16.dp))

               Row(modifier = Modifier.fillMaxSize()) {
                  Detalles(
                     sesion = state.sesiones.first(),
                     modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                  )

                  Button(
                     modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.Bottom),
                     onClick = {
                        trailerVisible = true
                     },
                     colors = ButtonDefaults.colors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        focusedContainerColor = Color.Red,
                        focusedContentColor = Color.White,
                     ),
                     glow = ButtonDefaults.glow(focusedGlow = Glow(Color.White, 8.dp)),
                  ) {
                     Icon(imageVector = Icons.Default.PlayArrow, "")
                  }
               }
            }
         }
      }

      if (trailerVisible) {

         BackHandler {
            trailerVisible = false
         }
         YoutubeView(
            state.sesiones.first().video.substringAfterLast("/"),
            LocalLifecycleOwner.current
         )

      }
   }
}

@Preview(
   showBackground = true,
   showSystemUi = true,
   device = Devices.TV_1080p,
   backgroundColor = 0xFFFFFFFF,
   widthDp = 1920,
   heightDp = 1080
)
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
   )
}




package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.data.Utilidades
import com.senkou.carteleraa7.data.model.Sesion
import com.senkou.carteleraa7.presentation.theme.Typography
import com.senkou.carteleraa7.presentation.theme.color_blanco
import com.senkou.carteleraa7.presentation.theme.fondoFechaEstreno
import com.senkou.carteleraa7.presentation.theme.fondo_lista
import com.senkou.carteleraa7.presentation.theme.transparente

@Composable
fun DetallePelicula(navController: NavHostController, sesiones: List<Sesion>) {

   Column(
      modifier = Modifier
         .fillMaxSize()
         .background(fondo_lista)
         .verticalScroll(state = rememberScrollState(), true),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      val context = LocalContext.current

      Box {
         SubcomposeAsyncImage(
            model = sesiones.first().getUrlCartel(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
               .height(200.dp)
               .fillMaxWidth(),
            contentDescription = "",
            loading = { CircularProgressIndicator() }
         )

         Text(
            modifier = Modifier
               .fillMaxWidth()
               .background(
                  color = fondoFechaEstreno,
//                        shape = RoundedCornerShape(5.dp)
               )
               .padding(16.dp, 8.dp, 16.dp, 8.dp),
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = Typography.body2,
            text = sesiones[0].titulo
         )

         Button(modifier = Modifier
            .padding(0.dp)
            .align(Alignment.TopStart)
            .offset(x = (-8).dp, y = (-8).dp),
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(transparente),
            contentPadding = PaddingValues(0.dp),
            onClick = { navController.popBackStack() }) {
            Icon(
               modifier = Modifier
                  .height(24.dp)
                  .width(24.dp),
               painter = painterResource(R.drawable.baseline_arrow_back_48),
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
               Utilidades.playTrailer(context, sesiones[0].video)
            },
            backgroundColor = Color.Red,
            contentColor = Color.White
         ) {
            Icon(painterResource(R.drawable.ic_play_arrow_white_48dp), "")
         }
      }

      Spacer(modifier = Modifier.height(30.dp))

      Column(
         Modifier
            .padding(16.dp, 6.dp)
            .wrapContentHeight()
      ) {
         FilaFechas(sesiones = sesiones)

         sesiones.first().crearDetalles().forEach { detalles ->

            Text(
               modifier = Modifier
                  .fillMaxWidth()
                  .wrapContentHeight(),
               color = Color.White,
               textAlign = TextAlign.Start,
               style = Typography.h1,
               text = detalles
            )

            Spacer(modifier = Modifier.height(6.dp))
         }

         Spacer(modifier = Modifier.height(28.dp))
      }
   }
}
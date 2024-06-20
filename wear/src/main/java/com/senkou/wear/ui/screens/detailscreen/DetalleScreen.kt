package com.senkou.wear.ui.screens.detailscreen

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.senkou.wear.data.model.Sesion
import com.senkou.wear.ui.theme.Typography
import com.senkou.wear.ui.theme.fondo_lista
import com.senkou.wear.ui.theme.resalte_ticket

@Composable
fun DetallePelicula(sesiones: List<Sesion>) {

   Column(
      modifier = Modifier
         .fillMaxSize()
         .background(fondo_lista),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {


      Text(
         modifier = Modifier
            .fillMaxWidth()
            .background(
               color = resalte_ticket,
               shape = RoundedCornerShape(5.dp)
            )
            .padding(16.dp, 8.dp, 16.dp, 8.dp),
         maxLines = 1,
         textAlign = TextAlign.Center,
         color = Color.White,
         style = Typography.body2,
         text = sesiones[0].titulo
      )

      Column(
         Modifier
            .padding(16.dp, 6.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), true)
      ) {

         FilaFechas(sesiones = sesiones)

         sesiones.first().crearDetalles().forEach { detalles ->

            Text(
               modifier = Modifier
                  .fillMaxWidth()
                  .wrapContentHeight(),
               color = Color.White,
               textAlign = TextAlign.Start,
               style = Typography.display1,
               text = detalles
            )

            Spacer(modifier = Modifier.height(6.dp))
         }

         Spacer(modifier = Modifier.height(28.dp))
      }
   }

}
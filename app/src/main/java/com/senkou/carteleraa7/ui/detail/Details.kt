package com.senkou.carteleraa7.ui.detail

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.carteleraa7.ui.theme.Typography
import com.senkou.domain.common.crearDetalles
import java.net.URLDecoder

@Composable
fun Details(
   state: DetailViewModel.UiState,
   padding: PaddingValues,
   onPlayTrailer: (video: String) -> Unit
) {
   if (state.sessions.isNotEmpty()) {
      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(state = rememberScrollState(), true),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Box {
            AsyncImage(
               model = URLDecoder.decode(state.sessions.first().background, "UTF-8"),
               contentScale = ContentScale.Crop,
               modifier = Modifier
                  .height(200.dp)
                  .fillMaxWidth(),
               contentDescription = "",
            )

            FloatingActionButton(
               modifier = Modifier
                  .padding(end = 10.dp)
                  .align(Alignment.BottomEnd)
                  .offset(y = (25).dp),
               onClick = { onPlayTrailer(state.sessions.first().video) },
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

            SessionsRow(state.sessions, state.sessionsDays)

            state.sessions.first().crearDetalles().forEach { details ->

               Text(
                  modifier = Modifier
                     .fillMaxWidth()
                     .wrapContentHeight(),
                  color = Color.White,
                  textAlign = TextAlign.Start,
                  style = Typography.labelSmall,
                  text = details
               )

               Spacer(modifier = Modifier.height(6.dp))
            }

            Spacer(modifier = Modifier.height(28.dp))
         }
      }
   }
}
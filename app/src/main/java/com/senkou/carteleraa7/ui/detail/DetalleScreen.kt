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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.senkou.carteleraa7.ui.common.LoadingIndicator
import com.senkou.carteleraa7.ui.common.Result
import com.senkou.carteleraa7.ui.common.ifSuccess
import com.senkou.carteleraa7.ui.theme.Typography
import com.senkou.carteleraa7.ui.theme.fondoFechaEstreno
import com.senkou.domain.common.crearDetalles
import java.net.URLDecoder

@Composable
fun DetallePelicula(
   model: DetalleViewModel,
   onBack: () -> Unit
) {
   val state by model.uiState.collectAsStateWithLifecycle()

   DetallePelicula(
      state = state,
      onBack = onBack,
      onPlayTrailer = { video -> model.playTrailer(video) }
   )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePelicula(
   state: Result<DetalleViewModel.UiState>,
   onBack: () -> Unit,
   onPlayTrailer: (video: String) -> Unit

) {
   Screen {
      Scaffold(
         topBar = {
            TopAppBar(
               title = {
                  state.ifSuccess {
                     Text(
                        text = it.sesiones.firstOrNull()?.titulo ?: "",
                        color = MaterialTheme.colorScheme.onSurface
                     )
                  }
               },
               navigationIcon = {
                  IconButton(onClick = onBack) {
                     Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                     )
                  }
               },
               colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = fondoFechaEstreno,
               )
            )
         },
         contentWindowInsets = WindowInsets.safeDrawing
      ) { padding ->

         when (state) {
            Result.Loading -> LoadingIndicator(padding)
            is Result.Error -> Text(text = state.throwable.message.orEmpty())
            is Result.Success -> Detalles(state.data, padding, onPlayTrailer)
         }
      }
   }
}

@Composable
fun Detalles(
   state: DetalleViewModel.UiState,
   padding: PaddingValues,
   onPlayTrailer: (video: String) -> Unit
) {
   if (state.sesiones.isNotEmpty()) {
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
               model = URLDecoder.decode(state.sesiones.first().background, "UTF-8"),
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
               onClick = { onPlayTrailer(state.sesiones.first().video) },
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

            FilaFechas(state.sesiones, state.diasSesiones)

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
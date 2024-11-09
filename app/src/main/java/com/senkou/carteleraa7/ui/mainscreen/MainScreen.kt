package com.senkou.carteleraa7.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senkou.carteleraa7.ui.Screen
import com.senkou.carteleraa7.ui.theme.resalte_ticket
import com.senkou.data.MoviesRepository
import com.senkou.framework.remote.WebMovieDatasource
import com.senkou.usecases.CargarCarteleraUseCase
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
   model: PeliListViewModel,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {

   Screen {
      Scaffold(
         containerColor = MaterialTheme.colorScheme.surface,
         contentWindowInsets = WindowInsets.safeDrawing
      ) { paddingValues ->


         Column(
            modifier = Modifier
               .fillMaxWidth()
               .padding(paddingValues)
         ) {

            var tabIndex by rememberSaveable { mutableIntStateOf(0) }
            val tabData = listOf("CARTELERA", "PROX. ESTRENOS")
            val pagerState = rememberPagerState(
               initialPage = 0,
               initialPageOffsetFraction = 0f,
            ) { tabData.size }

            TabRow(selectedTabIndex = tabIndex,
               modifier = Modifier.background(color = resalte_ticket),
               indicator = { tabPositions ->
                  SecondaryIndicator(
                     modifier = Modifier.tabIndicatorOffset(
                        currentTabPosition = tabPositions[tabIndex],
                     ),
                     color = MaterialTheme.colorScheme.onBackground,
                  )
               }
            ) {
               tabData.forEachIndexed { index, title ->
                  val coroutineScope = rememberCoroutineScope()
                  Tab(
                     selected = tabIndex == index,
                     onClick = {
                        tabIndex = index
                        coroutineScope.launch {
                           pagerState.scrollToPage(index)
                        }
                     },
                     text = { Text(text = title, color = MaterialTheme.colorScheme.onBackground) })
               }
            }

            HorizontalPager(
               state = pagerState,
            ) { page ->
               val state by model.state.collectAsStateWithLifecycle()

               tabIndex = page
               when (page) {
                  0 -> Cartelera(state.peliculas) { idEspectaculo -> onMovieClicked(idEspectaculo) }
                  1 -> Cartelera(state.proximosEstrenos) {}
               }
            }
         }
      }
   }
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {
   val moviesRepository = MoviesRepository(WebMovieDatasource())
   val peliListViewModel = PeliListViewModel(CargarCarteleraUseCase(moviesRepository))
   MainScreen(peliListViewModel) {}
}
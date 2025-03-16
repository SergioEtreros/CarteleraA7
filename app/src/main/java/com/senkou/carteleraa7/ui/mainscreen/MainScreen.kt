package com.senkou.carteleraa7.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senkou.carteleraa7.ui.Screen
import com.senkou.carteleraa7.ui.common.LoadingIndicator
import com.senkou.carteleraa7.ui.common.Result
import com.senkou.carteleraa7.ui.theme.ticketHighlight
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
   model: MovieListViewModel,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {
   val state by model.state.collectAsStateWithLifecycle()

   MainScreen (state = state, onMovieClicked = onMovieClicked)
}

@Composable
fun MainScreen(
   state: Result<MovieListViewModel.UiState>,
   onMovieClicked: (idEspectaculo: Int) -> Unit,
) {
   Screen {
      Scaffold(
         containerColor = MaterialTheme.colorScheme.surface,
         contentWindowInsets = WindowInsets.safeDrawing
      ) { paddingValues ->
         when (state) {
            Result.Loading -> LoadingIndicator(paddingValues)
            is Result.Error -> Text(text = state.throwable.message.orEmpty())
            is Result.Success -> {
              CarteleraPage(paddingValues, state.data, onMovieClicked)
            }
         }
      }
   }
}

@Composable
fun CarteleraPage(
   paddingValues: PaddingValues,
   state: MovieListViewModel.UiState,
   onMovieClicked: (idEspectaculo: Int) -> Unit
) {
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
         modifier = Modifier.background(color = ticketHighlight),
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
         tabIndex = page
         when (page) {
            0 -> MovieList(state.movies) { idEspectaculo -> onMovieClicked(idEspectaculo) }
            1 -> MovieList(state.upcominMovies) {}
         }
      }
   }
}

//@Preview(showSystemUi = false)
//@Composable
//fun MainScreenPreview() {
//   val moviesRepository = MoviesRepository(WebMovieDatasource())
//   val peliListViewModel = PeliListViewModel(CargarCarteleraUseCase(moviesRepository))
//   MainScreen(peliListViewModel) {}
//}
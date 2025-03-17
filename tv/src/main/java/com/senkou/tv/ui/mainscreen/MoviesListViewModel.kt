package com.senkou.tv.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.model.Pelicula
import com.senkou.tv.ui.common.stateAsResultIn
import com.senkou.usecases.DeleteMoviesUseCase
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.usecases.LoadUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
   loadMoviesUseCase: LoadMoviesUseCase,
   loadUpcomingMoviesUseCase: LoadUpcomingMoviesUseCase,
   deleteMoviesUseCase: DeleteMoviesUseCase
) : ViewModel() {

   val state = loadMoviesUseCase()
      .combine(loadUpcomingMoviesUseCase()) { movies, upcomingMovies ->
         UiState(movies = movies, upcomingMovies = upcomingMovies)
      }.stateAsResultIn(viewModelScope)

   init {
      viewModelScope.launch {
         deleteMoviesUseCase()
      }
   }

   data class UiState(
      val movies: List<Pelicula> = emptyList(),
      val upcomingMovies: List<Pelicula> = emptyList()
   )
}


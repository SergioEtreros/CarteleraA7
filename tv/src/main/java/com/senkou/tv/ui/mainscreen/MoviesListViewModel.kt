package com.senkou.tv.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.model.Pelicula
import com.senkou.tv.ui.common.stateAsResultIn
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.usecases.LoadUpcomingMoviesUseCase
import kotlinx.coroutines.flow.combine

//@HiltViewModel
class MoviesListViewModel(
   loadMoviesUseCase: LoadMoviesUseCase,
   loadUpcomingMoviesUseCase: LoadUpcomingMoviesUseCase
) : ViewModel() {

   val state = loadMoviesUseCase()
      .combine(loadUpcomingMoviesUseCase()) { movies, upcomingMovies ->
         UiState(movies = movies, upcomingMovies = upcomingMovies)
      }.stateAsResultIn(viewModelScope)

   data class UiState(
      val movies: List<Pelicula> = emptyList(),
      val upcomingMovies: List<Pelicula> = emptyList()
   )
}


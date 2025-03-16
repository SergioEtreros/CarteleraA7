package com.senkou.carteleraa7.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.ui.common.Result
import com.senkou.carteleraa7.ui.common.stateAsResultIn
import com.senkou.domain.model.Pelicula
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.usecases.LoadUpcomingMoviesUseCase
import kotlinx.coroutines.flow.combine

//@HiltViewModel
class MovieListViewModel(
   loadMoviesUseCase: LoadMoviesUseCase,
   loadUpcomingMoviesUseCase: LoadUpcomingMoviesUseCase
) : ViewModel() {


   val state = loadMoviesUseCase()
      .combine(loadUpcomingMoviesUseCase()) { movies, upcominMovies ->
         UiState(movies,upcominMovies)
      }.stateAsResultIn(
         scope = viewModelScope,
         initialValue = Result.Success(UiState())
      )

   data class UiState(
      val movies: List<Pelicula> = emptyList(),
      val upcominMovies: List<Pelicula> = emptyList()
   )
}
package com.senkou.carteleraa7.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.ui.common.Result
import com.senkou.carteleraa7.ui.common.stateAsResultIn
import com.senkou.domain.model.Pelicula
import com.senkou.usecases.DeleteMoviesUseCase
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.usecases.LoadUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
   loadMoviesUseCase: LoadMoviesUseCase,
   loadUpcomingMoviesUseCase: LoadUpcomingMoviesUseCase,
   deleteMoviesUseCase: DeleteMoviesUseCase
) : ViewModel() {

   val state = loadMoviesUseCase()
      .combine(loadUpcomingMoviesUseCase()) { movies, upcominMovies ->
         UiState(movies,upcominMovies)
      }.stateAsResultIn(
         scope = viewModelScope,
         initialValue = Result.Success(UiState())
      )

   init {
      viewModelScope.launch {
         deleteMoviesUseCase()
      }
   }

   data class UiState(
      val movies: List<Pelicula> = emptyList(),
      val upcominMovies: List<Pelicula> = emptyList()
   )
}
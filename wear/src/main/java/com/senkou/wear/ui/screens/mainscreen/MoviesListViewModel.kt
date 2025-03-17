package com.senkou.wear.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.usecases.DeleteMoviesUseCase
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.wear.ui.common.Result
import com.senkou.wear.ui.common.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
   loadMoviesUseCase: LoadMoviesUseCase,
   deleteMoviesUseCase: DeleteMoviesUseCase
) : ViewModel() {

   val state = loadMoviesUseCase().stateAsResultIn(
      scope = viewModelScope,
      initialValue = Result.Success(emptyList())
   )

   init {
      viewModelScope.launch {
         deleteMoviesUseCase()
      }
   }
}
package com.senkou.wear.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.wear.ui.common.Result
import com.senkou.wear.ui.common.stateAsResultIn

//@HiltViewModel
class MoviesListViewModel(
   loadMoviesUseCase: LoadMoviesUseCase,
) : ViewModel() {

   val state = loadMoviesUseCase().stateAsResultIn(
      scope = viewModelScope,
      initialValue = Result.Success(emptyList())
   )
}
package com.senkou.carteleraa7.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.domain.common.getDaysSessions
import com.senkou.domain.model.Sesion
import com.senkou.usecases.LoadDetailUseCase
import com.senkou.usecases.PlayTrailerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
   @Named("movieId") movieId: Int,
   loadDetailUseCase: LoadDetailUseCase,
   private val playTrailerUseCase: PlayTrailerUseCase,
) : ViewModel() {

   val uiState = loadDetailUseCase(movieId).map { sessions ->
      UiState(sessions = sessions, sessions.getDaysSessions())
   }

   fun playTrailer(video: String) {
      viewModelScope.launch {
         playTrailerUseCase(video)
      }
   }

   data class UiState(
      val sessions: List<Sesion> = emptyList(),
      val sessionsDays: List<String> = emptyList(),
   )
}

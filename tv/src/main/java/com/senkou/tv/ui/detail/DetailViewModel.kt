package com.senkou.tv.ui.detail

import androidx.lifecycle.ViewModel
import com.senkou.domain.common.getDaysSessions
import com.senkou.domain.model.Sesion
import com.senkou.usecases.LoadDetailUseCase
import kotlinx.coroutines.flow.map

//@HiltViewModel
class DetailViewModel(
   movieId: Int,
   loadDetailUseCase: LoadDetailUseCase,
) : ViewModel() {

   val uiState = loadDetailUseCase(movieId).map { sessions ->
      UiState(sessions = sessions, sessions.getDaysSessions())
   }

   data class UiState(
      val sessions: List<Sesion> = emptyList(),
      val sessionsDays: List<String> = emptyList(),
   )
}


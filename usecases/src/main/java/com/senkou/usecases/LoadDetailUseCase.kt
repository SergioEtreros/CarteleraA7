package com.senkou.usecases

import com.senkou.data.MoviesRepository
import javax.inject.Inject

class LoadDetailUseCase @Inject constructor(
   private val repository: MoviesRepository
) {
   operator fun invoke(movieId: Int) = repository.getSessions(movieId)
}
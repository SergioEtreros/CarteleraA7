package com.senkou.usecases

import com.senkou.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
   suspend operator fun invoke() = withContext(Dispatchers.IO) {
      repository.deleteMovies()
      repository.deleteUpcomingMovies()
   }
}
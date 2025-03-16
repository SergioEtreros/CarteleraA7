package com.senkou.usecases

import com.senkou.data.MoviesRepository

class LoadDetailUseCase(private val repository: MoviesRepository) {
   operator fun invoke(movieId: Int) = repository.getSesiones(movieId)
}
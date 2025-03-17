package com.senkou.usecases

import com.senkou.data.MoviesRepository
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
   operator fun invoke() = repository.peliculas
}
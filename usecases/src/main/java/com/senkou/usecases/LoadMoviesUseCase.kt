package com.senkou.usecases

import com.senkou.data.MoviesRepository

class LoadMoviesUseCase(private val repository: MoviesRepository) {
   operator fun invoke() = repository.peliculas
}
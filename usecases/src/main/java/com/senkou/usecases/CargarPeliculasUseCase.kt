package com.senkou.usecases

import com.senkou.data.MoviesRepository

class CargarPeliculasUseCase(private val repository: MoviesRepository) {
   operator fun invoke() = repository.peliculas
}
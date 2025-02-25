package com.senkou.usecases

import com.senkou.data.MoviesRepository

class CargarProximosEstrenosUseCase(private val repository: MoviesRepository) {
   operator fun invoke() = repository.proximosEstrenos
}
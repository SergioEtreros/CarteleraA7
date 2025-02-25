package com.senkou.usecases

import com.senkou.data.MoviesRepository

class CargarDetalleUseCase(private val repository: MoviesRepository) {
   operator fun invoke(idEspectaculo: Int) = repository.getSesiones(idEspectaculo)
}
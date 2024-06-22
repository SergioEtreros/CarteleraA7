package com.senkou.usecases

import com.senkou.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CargarDetalleUseCase(private val repository: MoviesRepository) {
   suspend operator fun invoke(idEspectaculo: Int) = withContext(Dispatchers.IO) {
      repository.getSesiones().filter { it.iDEspectaculo == idEspectaculo }
   }
}
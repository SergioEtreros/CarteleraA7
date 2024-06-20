package com.senkou.carteleraa7.usecase

import com.senkou.carteleraa7.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CargarDetalleUseCase(private val repository: MoviesRepository) {
   suspend operator fun invoke(idEspectaculo: Int) = withContext(Dispatchers.IO) {
      repository.getSesiones().filter { it.iDEspectaculo == idEspectaculo }
   }
}
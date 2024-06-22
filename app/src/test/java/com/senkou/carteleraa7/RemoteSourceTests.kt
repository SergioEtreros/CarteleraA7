package com.senkou.carteleraa7

import com.senkou.data.MoviesRepository
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.usecases.CargarDetalleUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemoteSourceTests {

   @Test
   fun testCartelera() {
      val useCase =
         CargarCarteleraUseCase(MoviesRepository(com.senkou.framework.remote.WebMovieDatasource()))
      runBlocking {
         val cartelera = useCase()
         assert(cartelera.pelis.isNotEmpty())
         assert(cartelera.proximosEstrenos.isNotEmpty())
      }
   }

   @Test
   fun testSesiones() {
      val useCase =
         CargarDetalleUseCase(MoviesRepository(com.senkou.framework.remote.WebMovieDatasource()))
      runBlocking {
         val sesiones = useCase(12653)
         assert(sesiones.isNotEmpty())
      }
   }
}

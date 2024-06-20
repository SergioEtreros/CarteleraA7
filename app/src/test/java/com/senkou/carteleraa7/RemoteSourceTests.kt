package com.senkou.carteleraa7

import com.senkou.carteleraa7.data.MoviesRepository
import com.senkou.carteleraa7.framework.remote.WebMovieDatasource
import com.senkou.carteleraa7.usecase.CargarCarteleraUseCase
import com.senkou.carteleraa7.usecase.CargarDetalleUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemoteSourceTests {

   @Test
   fun testCartelera() {
      val useCase = CargarCarteleraUseCase(MoviesRepository(WebMovieDatasource()))
      runBlocking {
         val cartelera = useCase()
         assert(cartelera.pelis.isNotEmpty())
         assert(cartelera.proximosEstrenos.isNotEmpty())
      }
   }

   @Test
   fun testSesiones() {
      val useCase = CargarDetalleUseCase(MoviesRepository(WebMovieDatasource()))
      runBlocking {
         val sesiones = useCase(12653)
         assert(sesiones.isNotEmpty())
      }
   }
}

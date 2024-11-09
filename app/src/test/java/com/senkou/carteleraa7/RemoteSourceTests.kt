package com.senkou.carteleraa7

import com.senkou.data.MoviesRepository
import com.senkou.framework.remote.WebMovieDatasource
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.usecases.CargarDetalleUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

//import org.junit.jupiter.api.Test

class RemoteSourceTests {

   @Test
   fun testCartelera() {
      val useCase =
         CargarCarteleraUseCase(MoviesRepository(WebMovieDatasource()))
      runBlocking {
         val cartelera = useCase()
         assert(cartelera.peliculas.isNotEmpty())
         assert(cartelera.proximosEstrenos.isNotEmpty())
      }
   }

   @Test
   fun testSesiones() {
      val useCase =
         CargarDetalleUseCase(MoviesRepository(WebMovieDatasource()))
      runBlocking {
         val sesiones = useCase(12653)
         assert(sesiones.isNotEmpty())
      }
   }
}

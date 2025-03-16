package com.senkou.domain.model

class Cartelera(
   val peliculas: List<Pelicula>,
   val proximosEstrenos: List<Pelicula>,
   val sesiones: List<Sesion> = emptyList()
)
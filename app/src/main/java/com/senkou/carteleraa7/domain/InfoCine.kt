package com.senkou.carteleraa7.domain

import com.google.gson.annotations.SerializedName

class InfoCine(
   @SerializedName("Cartelera") val pelis: List<Pelicula>,
   @SerializedName("Sesiones") val sesiones: List<Sesion>,
   @SerializedName("Proximamente") val proximosEstrenos: List<Pelicula>
)

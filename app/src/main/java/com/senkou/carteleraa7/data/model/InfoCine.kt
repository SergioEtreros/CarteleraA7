package com.senkou.carteleraa7.data.model

import com.google.gson.annotations.SerializedName

class InfoCine(
    @SerializedName("Cartelera") val pelis: List<Pelicula>,
    @SerializedName("Sesiones") val sesiones: List<Sesion>,
    @SerializedName("Proximamente") val proximosEstrenos: List<Pelicula>
)
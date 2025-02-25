package com.senkou.framework.remote.arte7.model

import com.google.gson.annotations.SerializedName

class InfoCine(

   @SerializedName("Cartelera")
   val pelis: List<PeliculaRemote>,

   @SerializedName("Sesiones")
   val sesiones: List<SesionRemote>,

   @SerializedName("Proximamente")
   val proximosEstrenos: List<PeliculaRemote>?
)

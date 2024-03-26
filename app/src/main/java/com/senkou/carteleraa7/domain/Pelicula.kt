package com.senkou.carteleraa7.domain

import com.google.gson.annotations.SerializedName
import com.senkou.carteleraa7.framework.common.Utilidades

data class Pelicula(
   @SerializedName("Cartel")
   val cartel: String,
   @SerializedName("FechaEstreno")
   val fechaEstreno: String,
   @SerializedName("ID_Espectaculo")
   val iDEspectaculo: Int,
   @SerializedName("Titulo")
   val titulo: String
) {
   fun getUrlCartel(): String =
      cartel.takeIf { cartel.contains("/") } ?: (Utilidades.RUTA_CARTELES + cartel)
}
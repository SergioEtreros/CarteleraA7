package com.senkou.carteleraa7.Clases

import com.google.gson.annotations.SerializedName

data class Json4Kotlin_Base(

        @SerializedName("Cartelera") val pelis: List<Peli>
)
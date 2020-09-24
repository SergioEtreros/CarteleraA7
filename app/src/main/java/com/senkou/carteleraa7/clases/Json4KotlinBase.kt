package com.senkou.carteleraa7.clases

import com.google.gson.annotations.SerializedName

data class Json4KotlinBase(
        @SerializedName("Cartelera") val pelis: List<Peli>
)
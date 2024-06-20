package com.senkou.wear.data.model

import com.google.gson.annotations.SerializedName
import com.senkou.wear.data.model.ProximoEstreno

class ResponseProximosEstrenos(
   @SerializedName("Proximamente") val proximosEstrenos: List<ProximoEstreno>
)
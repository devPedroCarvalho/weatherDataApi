package com.keezag.weatherdataapi.model


import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat")
    val lat: Int,
    @SerializedName("lon")
    val lon: Int
)
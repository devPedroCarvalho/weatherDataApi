package com.keezag.weatherdataapi

import com.keezag.weatherdataapi.model.OpenWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherInterface {
    @GET ("data/2.5/weather?")
    fun getCurrentWeatherByCity(@Query("q")cidade: String,
                                @Query("units") units: String,
                                @Query("APPID") app_id: String
                                ): Call<OpenWeatherResponse>
}
package com.techmania.weatherproject.data.networking

import com.techmania.weatherproject.data.networking.Dto.WeatherInfoListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApi {
    //@GET("v1/forecast?hourly=temperature_2m,apparent_temperature,precipitation,weather_code,wind_speed_10m")
    @GET("v1/forecast?current=temperature_2m,apparent_temperature,precipitation,weather_code,wind_speed_10m&hourly=temperature_2m,apparent_temperature,precipitation,weather_code,wind_speed_10m&daily=weather_code,temperature_2m_max,apparent_temperature_max,precipitation_sum,wind_speed_10m_max&timezone=auto")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherInfoListDto?
}
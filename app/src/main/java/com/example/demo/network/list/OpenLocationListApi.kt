package com.example.demo.network.list

import com.example.demo.network.responses.LocationOpen
import retrofit2.Call
import retrofit2.http.GET

interface OpenLocationListApi {
    @GET("/api/location")
    fun getLocation(
    ): Call<LocationOpen>
}

package com.example.demo.network.details

import com.example.demo.domain.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenLocationDetailsApi {

    @GET("/api/location/{id}")
    fun getLocation(
            @Path("id") id: Int
    ): Call<Location>
}
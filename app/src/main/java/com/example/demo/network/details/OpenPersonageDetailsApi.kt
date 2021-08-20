package com.example.demo.network.details

import com.example.demo.domain.Personage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenPersonageDetailsApi {

    @GET("/api/character/{id}")
    fun getPersonage(
            @Path("id") id: Int
    ): Call<Personage>
}
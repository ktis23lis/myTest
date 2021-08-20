package com.example.demo.network.details

import com.example.demo.domain.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenEpisodeDetailsApi {

    @GET("/api/episode/{id}")
    fun getEpisode(
            @Path("id") id: Int
    ): Call<Episode>

    @GET("/api/episode/{id}")
    fun getEpisodeArr(
            @Path("id") id: Int
    ): Call<ArrayList<Episode>>
}
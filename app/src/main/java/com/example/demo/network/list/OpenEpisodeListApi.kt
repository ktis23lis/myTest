package com.example.demo.network.list

import com.example.demo.network.responses.EpisodeOpen
import retrofit2.Call
import retrofit2.http.GET

interface OpenEpisodeListApi {
    @GET("/api/episode")
    fun getEpisode(
    ): Call<EpisodeOpen>
}

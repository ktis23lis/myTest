package com.example.demo.network

import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.network.responses.EpisodeOpen
import com.example.demo.network.responses.LocationOpen
import com.example.demo.network.responses.PersonageOpen
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("/api/episode")
    fun getEpisodeList(): Call<EpisodeOpen>

    @GET("/api/location")
    fun getLocationList(): Call<LocationOpen>

    @GET("/api/character")
    fun getPersonageList(): Call<PersonageOpen>

    @GET("/api/episode/{id}")
    fun getEpisodeDetail(@Path("id") id: Int): Call<Episode>

    @GET("/api/location/{id}")
    fun getLocationDetail(@Path("id") id: Int): Call<Location>

    @GET("/api/character/{id}")
    fun getPersonageDetail(@Path("id") id: Int): Call<Personage>



}
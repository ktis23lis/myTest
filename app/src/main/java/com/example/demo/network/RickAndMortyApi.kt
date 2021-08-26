package com.example.demo.network

import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.network.responses.EpisodeOpen
import com.example.demo.network.responses.LocationOpen
import com.example.demo.network.responses.PersonageOpen
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("/api/episode")
    fun getEpisodeList(): Single<EpisodeOpen>

    @GET("/api/location")
    fun getLocationList(): Single<LocationOpen>

    @GET("/api/character")
    fun getPersonageList(): Single<PersonageOpen>

    @GET("/api/episode/{id}")
    fun getEpisodeDetail(@Path("id") id: Int): Observable<Episode>

    @GET("/api/location/{id}")
    fun getLocationDetail(@Path("id") id: Int): Observable<Location>

    @GET("/api/character/{id}")
    fun getPersonageDetail(@Path("id") id: Int): Observable<Personage>

}
package com.example.demo.network.list

import com.example.demo.network.responses.PersonageOpen
import retrofit2.Call
import retrofit2.http.GET

interface OpenPersonageListApi {
    @GET("/api/character")
     fun getPersonage(
    ): Call<PersonageOpen>
}
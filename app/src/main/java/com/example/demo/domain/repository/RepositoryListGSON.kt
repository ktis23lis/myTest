package com.example.demo.domain.repository

import android.os.Handler
import android.os.Looper
import com.example.demo.domain.*
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.network.responses.PersonageOpen
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

class RepositoryListGSON : RepositoryList {

    private val gson = Gson()
    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private var personageData = ArrayList<Personage>()

    override fun getPersonageArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Personage>>) -> Unit
    ) {
        executor.execute {
            val url = URL("https://rickandmortyapi.com/api/character")
            val connection = url.openConnection() as HttpURLConnection
            try{
                with(connection){
                    requestMethod="GET"
                    readTimeout = 30_00
                    val result = gson.fromJson(
                        inputStream.bufferedReader(),
                        PersonageOpen::class.java
                    )
                    val personageList = result.personageList
                    for(i in personageList){
                        val personageImage = i.personageImage
                        val personageName = i.personageName
                        val personageSpecies = i.personageSpecies
                        val personageStatus = i.personageStatus
                        val personageGender = i.personageGender
//                        personageData.add(Personage(personageImage, personageName, personageSpecies, personageStatus, personageGender))
                    }
                    mainThreadHandler.post {
                        callback(SuccessList(personageData))
                    }
                }
            }finally {
                connection.disconnect()
            }
        }
    }

    override fun getLocationArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Location>>) -> Unit
    ) {


    }

    override fun getEpisodeArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Episode>>) -> Unit
    ) {

    }
}
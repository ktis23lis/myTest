package com.example.demo.domain

import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.SuccessList
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.Executor

interface RepositoryList {

    fun getPersonageArray(callback : (result : SuccessList<ArrayList<Personage>>) -> Unit)
    fun getLocationArray(callback : (result : SuccessList<ArrayList<Location>>) -> Unit)
    fun getEpisodeArray(callback : (result : SuccessList<ArrayList<Episode>>) -> Unit)

}
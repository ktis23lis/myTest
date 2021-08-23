package com.example.demo.domain.repository

import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import java.util.concurrent.Executor

interface RepositoryDetails {

    fun getPersonageDetails(id :Int, executor: Executor, callback : (result : SuccessDetails<Personage>) -> Unit)
    fun getLocationDetails(id :Int,executor: Executor, callback : (result : SuccessDetails<Location>) -> Unit)
    fun getEpisodeDetails(id :Int,executor: Executor, callback : (result : SuccessDetails<Episode>) -> Unit)

    fun getEpisodeForRV(episodes: ArrayList<String>,executor: Executor, callback : (result : SuccessList<ArrayList<Episode>>) -> Unit)
    fun getPersonageForRV(personages: ArrayList<String>,executor: Executor, callback : (result : SuccessList<ArrayList<Personage>>) -> Unit)

}
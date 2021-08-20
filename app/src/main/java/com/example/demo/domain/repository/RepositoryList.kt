package com.example.demo.domain

import com.example.demo.domain.repository.SuccessList
import java.util.concurrent.Executor

interface RepositoryList {

    fun getPersonageArray(executor: Executor, callback : (result : SuccessList<ArrayList<Personage>>) -> Unit)
    fun getLocationArray(executor: Executor, callback : (result : SuccessList<ArrayList<Location>>) -> Unit)
    fun getEpisodeArray(executor: Executor, callback : (result : SuccessList<ArrayList<Episode>>) -> Unit)
}
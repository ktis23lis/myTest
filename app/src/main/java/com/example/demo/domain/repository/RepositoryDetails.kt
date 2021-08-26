package com.example.demo.domain.repository

import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import java.util.concurrent.Executor

interface RepositoryDetails {

    fun getPersonageDetails(id :Int, callback : (result : SuccessDetails<Personage>) -> Unit)
    fun getLocationDetails(id :Int, callback : (result : SuccessDetails<Location>) -> Unit)
    fun getEpisodeDetails(id :Int, callback : (result : SuccessDetails<Episode>) -> Unit)
}
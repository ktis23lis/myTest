package com.example.demo.domain.repository

import android.os.Handler
import android.os.Looper
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.domain.RepositoryList
import com.example.demo.network.RestModule
import com.example.demo.network.UrlObject
import com.example.demo.network.responses.EpisodeOpen
import com.example.demo.network.responses.LocationOpen
import com.example.demo.network.responses.PersonageOpen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class RepositoryRetrofitList : RepositoryList {

    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private var personageData = ArrayList<Personage>()
    private var locationData = ArrayList<Location>()
    private var episodeData = ArrayList<Episode>()
    private val restModule = RestModule()

    override fun getPersonageArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Personage>>) -> Unit
    ) {
        restModule.provideRetrofitAndOkHttp(UrlObject.PERSONAGE_LIST_URL).getPersonageList()
            .enqueue(object : Callback<PersonageOpen> {
                override fun onResponse(
                    call: Call<PersonageOpen>,
                    response: Response<PersonageOpen>
                ) {
                    response.body()?.let { result ->
                        for (i in result.personageList) {
                            val personageId = i.personageId
                            val personageImage = i.personageImage
                            val personageName = i.personageName
                            val personageSpecies = i.personageSpecies
                            val personageStatus = i.personageStatus
                            val personageGender = i.personageGender
                            val personageOrigin = i.personagePersonageOrigin
                            val personageLocation = i.personageLocation
                            val personageEpisode = i.personageEpisode
                            personageData.add(
                                Personage(
                                    personageId,
                                    personageImage,
                                    personageName,
                                    personageSpecies,
                                    personageStatus,
                                    personageGender,
                                    personageOrigin,
                                    personageLocation,
                                    personageEpisode
                                )
                            )
                        }
                        mainThreadHandler.post {
                            callback(SuccessList(personageData))
                        }
                    }
                }

                override fun onFailure(call: Call<PersonageOpen>, t: Throwable) {
                }
            })
    }


    override fun getLocationArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Location>>) -> Unit
    ) {
        restModule.provideRetrofitAndOkHttp(UrlObject.LOCATION_LIST_URL).getLocationList()
            .enqueue(object : Callback<LocationOpen> {
                override fun onResponse(
                    call: Call<LocationOpen>,
                    response: Response<LocationOpen>
                ) {
                    response.body()?.let { result ->
                        for (i in result.locationList) {
                            val locationId = i.locationId
                            val locationName = i.locationName
                            val locationType = i.locationType
                            val locationDimension = i.locationDimension
                            val locationUrl = i.locationUrl
                            val locationResidents = i.locationResidents
                            locationData.add(
                                Location(
                                    locationId,
                                    locationName,
                                    locationType,
                                    locationDimension,
                                    locationUrl,
                                    locationResidents
                                )
                            )
                        }
                        mainThreadHandler.post {
                            callback(SuccessList(locationData))
                        }
                    }
                }

                override fun onFailure(call: Call<LocationOpen>, t: Throwable) {
                }
            })
    }

    override fun getEpisodeArray(
        executor: Executor,
        callback: (result: SuccessList<ArrayList<Episode>>) -> Unit
    ) {

        restModule.provideRetrofitAndOkHttp(UrlObject.EPISODE_LIST_URL).getEpisodeList()
            .enqueue(object : Callback<EpisodeOpen> {
                override fun onResponse(
                    call: Call<EpisodeOpen>,
                    response: Response<EpisodeOpen>
                ) {
                    response.body()?.let { result ->
                        for (i in result.episodeList) {
                            val episodeId = i.episodeId
                            val episodeName = i.episodeName
                            val episode = i.episode
                            val episodeAirFate = i.episodeAirFate
                            val episodeCharacter = i.episodeCharacters
                            episodeData.add(
                                Episode(
                                    episodeId,
                                    episodeName,
                                    episode,
                                    episodeAirFate,
                                    episodeCharacter
                                )
                            )
                        }
                        mainThreadHandler.post {
                            callback(SuccessList(episodeData))
                        }
                    }

                }

                override fun onFailure(call: Call<EpisodeOpen>, t: Throwable) {
                }
            })
    }
}

data class SuccessList<T>(val value: T)



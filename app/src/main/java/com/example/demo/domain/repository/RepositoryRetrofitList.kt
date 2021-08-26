 package com.example.demo.domain.repository

import android.util.Log
import com.example.demo.domain.RepositoryList
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.network.RestModule
import com.example.demo.network.UrlObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class RepositoryRetrofitList : RepositoryList {

    private var personageData = ArrayList<Personage>()
    private var locationData = ArrayList<Location>()
    private var episodeData = ArrayList<Episode>()
    private val restModule = RestModule()

    override fun getPersonageArray(callback: (result: SuccessList<ArrayList<Personage>>) -> Unit) {
        restModule.provideRetrofitAndOkHttp(UrlObject.PERSONAGE_LIST_URL).getPersonageList()
            .map {
                for (i in it.personageList) {
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
            }
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    callback(SuccessList(personageData))
                },
                { error -> Log.e("AAA", "${Thread.currentThread().name} Error") }
            )
    }

    override fun getLocationArray(callback: (result: SuccessList<ArrayList<Location>>) -> Unit) {
        restModule.provideRetrofitAndOkHttp(UrlObject.LOCATION_LIST_URL).getLocationList()
            .map {
                for (i in it.locationList) {
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
            }
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    callback(SuccessList(locationData))
                },
                { error -> Log.e("AAA", "${Thread.currentThread().name} Error") }
            )
    }

    override fun getEpisodeArray(callback: (result: SuccessList<ArrayList<Episode>>) -> Unit) {
        restModule.provideRetrofitAndOkHttp(UrlObject.EPISODE_LIST_URL).getEpisodeList()
            .map {
                for (i in it.episodeList) {
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
            }
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    callback(SuccessList(episodeData))
                },
                { error -> Log.e("AAA", "${Thread.currentThread().name} Error") }
            )
    }
}

data class SuccessList<T>(val value: T)

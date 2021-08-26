package com.example.demo.domain.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.network.RestModule
import com.example.demo.network.UrlObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryRetrofitDetails : RepositoryDetails {
    
    private val restModule = RestModule()

    override fun getPersonageDetails(
        id: Int,
        callback: (result: SuccessDetails<Personage>) -> Unit
    ) {
        restModule.provideRetrofitAndOkHttp(UrlObject.PERSONAGE_LIST_URL).getPersonageDetail(id)
            .map {
                Personage(
                    it.personageId,
                    it.personageImage,
                    it.personageName,
                    it.personageSpecies,
                    it.personageStatus,
                    it.personageGender,
                    it.personagePersonageOrigin,
                    it.personageLocation,
                    it.personageEpisode
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { personage ->
                    callback(SuccessDetails(personage))
                },
                { error -> Log.e("AAA", "${Thread.currentThread().name} Error") }
            )
    }


    override fun getLocationDetails(id: Int, callback: (result: SuccessDetails<Location>) -> Unit) {
        restModule.provideRetrofitAndOkHttp(UrlObject.LOCATION_LIST_URL).getLocationDetail(id)
            .map {
                    Location(
                    it.locationId,
                    it.locationName,
                    it.locationType,
                    it.locationDimension,
                    it.locationUrl,
                    it.locationResidents
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { location ->
//                    Log.e("AAA", "${Thread.currentThread().name} Success")
                    callback(SuccessDetails(location))
                },
                { error -> Log.e("AAA", "${Thread.currentThread().name} Error") }
            )
    }

    override fun getEpisodeDetails(id: Int, callback: (result: SuccessDetails<Episode>) -> Unit) {
        restModule.provideRetrofitAndOkHttp(UrlObject.EPISODE_LIST_URL).getEpisodeDetail(id)
            .map {
                    Episode(
                    it.episodeId,
                    it.episodeName,
                    it.episode,
                    it.episodeAirFate,
                    it.episodeCharacters
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { episode ->
//                    Log.e("AAA", "${Thread.currentThread().name} Success")
                    callback(SuccessDetails(episode))
                },
                { error -> Log.e("AAA", "${Thread.currentThread().name} Error") }
            )
    }
}

data class SuccessDetails<T>(val value: T)
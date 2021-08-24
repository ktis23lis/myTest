package com.example.demo.domain.repository

import android.os.Handler
import android.os.Looper
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.network.RestModule
import com.example.demo.network.UrlObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class RepositoryRetrofitDetails : RepositoryDetails {

    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private val restModule = RestModule()

    override fun getPersonageDetails(
        id: Int,
        executor: Executor,
        callback: (result: SuccessDetails<Personage>) -> Unit
    ) {
        restModule.provideRetrofitAndOkHttp(UrlObject.PERSONAGE_LIST_URL).getPersonageDetail(id)
            .enqueue(object : Callback<Personage> {
                override fun onResponse(
                    call: Call<Personage>,
                    response: Response<Personage>
                ) {
                    response.body()?.let { result ->
                        val personage = Personage(
                            result.personageId,
                            result.personageImage,
                            result.personageName,
                            result.personageSpecies,
                            result.personageStatus,
                            result.personageGender,
                            result.personagePersonageOrigin,
                            result.personageLocation,
                            result.personageEpisode
                        )
                        mainThreadHandler.post {
                            callback(SuccessDetails(personage))
                        }
                    }
                }

                override fun onFailure(call: Call<Personage>, t: Throwable) {
                }
            })
    }


    override fun getLocationDetails(
        id: Int,
        executor: Executor,
        callback: (result: SuccessDetails<Location>) -> Unit
    ) {
        restModule.provideRetrofitAndOkHttp(UrlObject.LOCATION_LIST_URL).getLocationDetail(id)
            .enqueue(object : Callback<Location> {
                override fun onResponse(
                    call: Call<Location>,
                    response: Response<Location>
                ) {
                    response.body()?.let { result ->
                        val location = Location(
                            result.locationId,
                            result.locationName,
                            result.locationType,
                            result.locationDimension,
                            result.locationUrl,
                            result.locationResidents
                        )
                        mainThreadHandler.post {
                            callback(SuccessDetails(location))
                        }
                    }
                }

                override fun onFailure(call: Call<Location>, t: Throwable) {
                }
            })
    }

    override fun getEpisodeDetails(
        id: Int,
        executor: Executor,
        callback: (result: SuccessDetails<Episode>) -> Unit
    ) {
        restModule.provideRetrofitAndOkHttp(UrlObject.EPISODE_LIST_URL).getEpisodeDetail(id)
            .enqueue(object : Callback<Episode> {
                override fun onResponse(
                    call: Call<Episode>,
                    response: Response<Episode>
                ) {
                    response.body()?.let { result ->
                        val episode = Episode(
                            result.episodeId,
                            result.episodeName,
                            result.episode,
                            result.episodeAirFate,
                            result.episodeCharacters
                        )
                        mainThreadHandler.post {
                            callback(SuccessDetails(episode))
                        }
                    }
                }

                override fun onFailure(call: Call<Episode>, t: Throwable) {
                }
            })
    }
}

data class SuccessDetails<T>(val value: T)
package com.example.demo.domain.repository

import android.os.Handler
import android.os.Looper
import com.example.demo.domain.Episode
import com.example.demo.domain.Location
import com.example.demo.domain.Origin
import com.example.demo.domain.Personage
import com.example.demo.network.details.OpenEpisodeDetailsApi
import com.example.demo.network.details.OpenLocationDetailsApi
import com.example.demo.network.details.OpenPersonageDetailsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class RepositoryRetrofitDetails : RepositoryDetails {

    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private val episodeArr = ArrayList<Episode>()
    private val personageArr = ArrayList<Personage>()

    companion object {
        const val PERSONAGE_LIST_URL = "https://rickandmortyapi.com/"
        const val LOCATION_LIST_URL = "https://rickandmortyapi.com/"
        const val EPISODE_LIST_URL = "https://rickandmortyapi.com/"
    }

    override fun getPersonageDetails(id: Int, executor: Executor, callback: (result: SuccessDetails<Personage>) -> Unit) {
        networkPersonageService(PERSONAGE_LIST_URL).getPersonage(id)
                .enqueue(object : Callback<Personage> {
                    override fun onResponse(
                            call: Call<Personage>,
                            response: Response<Personage>) {
                        response.body()?.let { result ->
                            val personage = Personage(
                                    result.personageId,
                                    result.personageImage,
                                    result.personageName,
                                    result.personageSpecies,
                                    result.personageStatus,
                                    result.personageGender,
                                    result.personageOrigin,
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

    override fun getLocationDetails(id: Int, executor: Executor, callback: (result: SuccessDetails<Location>) -> Unit) {
        networkLocationService(LOCATION_LIST_URL).getLocation(id)
                .enqueue(object : Callback<Location> {
                    override fun onResponse(
                            call: Call<Location>,
                            response: Response<Location>) {
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

    override fun getEpisodeDetails(id: Int, executor: Executor, callback: (result: SuccessDetails<Episode>) -> Unit) {
        networkEpisodeService(EPISODE_LIST_URL).getEpisode(id)
                .enqueue(object : Callback<Episode> {
                    override fun onResponse(
                            call: Call<Episode>,
                            response: Response<Episode>) {
                        response.body()?.let { result ->
                            val episode = Episode(
                                    result.episodeId,
                                    result.episodeName,
                                    result.episode,
                                    result.episodeAirFate
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

    override fun getEpisodeForRV(episodes: ArrayList<String>, executor: Executor, callback: (result: SuccessList<ArrayList<Episode>>) -> Unit) {
        for (episode in episodes) {
            val myId = (episode.substring(episode.lastIndexOf("/") + 1)).toInt()
            networkEpisodeService(EPISODE_LIST_URL).getEpisode(myId)
                    .enqueue(object : Callback<Episode> {
                        override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                            response.body()?.let { result ->
                                val episode = Episode(
                                        result.episodeId,
                                        result.episodeName,
                                        result.episode,
                                        result.episodeAirFate
                                )
                                episodeArr.add(episode)
                            }
                        }
                        override fun onFailure(call: Call<Episode>, t: Throwable) {
                        }
                    })
        }
        mainThreadHandler.post {
            callback(SuccessList(episodeArr))
        }
    }

    override fun getPersonageForRV(personages: ArrayList<String>, executor: Executor, callback: (result: SuccessList<ArrayList<Personage>>) -> Unit) {
        for (personage in personages) {
            val myId = (personage.substring(personage.lastIndexOf("/") + 1)).toInt()
            networkPersonageService(PERSONAGE_LIST_URL).getPersonage(myId)
                    .enqueue(object : Callback<Personage> {
                        override fun onResponse(call: Call<Personage>, response: Response<Personage>) {
                            response.body()?.let { result ->
                                val personage = Personage(
                                        result.personageId,
                                        result.personageImage,
                                        result.personageName,
                                        result.personageSpecies,
                                        result.personageStatus,
                                        result.personageGender,
                                        Origin(result.personageOrigin.name, result.personageOrigin.url),
                                        Location(result.personageLocation.locationId,
                                                result.personageLocation.locationName,
                                                result.personageLocation.locationType,
                                                result.personageLocation.locationDimension,
                                                result.personageLocation.locationUrl,
                                                result.personageLocation.locationResidents),
                                        result.personageEpisode
                                )
                                personageArr.add(personage)
                            }
                        }
                        override fun onFailure(call: Call<Personage>, t: Throwable) {
                        }
                    })
        }
        mainThreadHandler.post {
            callback(SuccessList(personageArr))
        }
    }


    private fun networkPersonageService(urlPersonage: String): OpenPersonageDetailsApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(urlPersonage)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(OpenPersonageDetailsApi::class.java)
    }

    private fun networkLocationService(urlLocation: String): OpenLocationDetailsApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(urlLocation)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(OpenLocationDetailsApi::class.java)
    }

    private fun networkEpisodeService(urlEpisode: String): OpenEpisodeDetailsApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(urlEpisode)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(OpenEpisodeDetailsApi::class.java)
    }
}

data class SuccessDetails<T>(val value: T)
package com.example.demo.domain.repository

import android.os.Handler
import android.os.Looper
import com.example.demo.domain.Episode
import com.example.demo.domain.Location
import com.example.demo.domain.Personage
import com.example.demo.domain.RepositoryList
import com.example.demo.network.list.OpenEpisodeListApi
import com.example.demo.network.list.OpenLocationListApi
import com.example.demo.network.list.OpenPersonageListApi
import com.example.demo.network.responses.EpisodeOpen
import com.example.demo.network.responses.LocationOpen
import com.example.demo.network.responses.PersonageOpen
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class RepositoryListRetrofitList : RepositoryList {

    companion object {
        const val PERSONAGE_LIST_URL = "https://rickandmortyapi.com/"
        const val LOCATION_LIST_URL = "https://rickandmortyapi.com/"
        const val EPISODE_LIST_URL = "https://rickandmortyapi.com/"
    }

    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private var personageData = ArrayList<Personage>()
    private var locationData = ArrayList<Location>()
    private var episodeData = ArrayList<Episode>()

    override fun getPersonageArray(executor: Executor, callback: (result: SuccessList<ArrayList<Personage>>) -> Unit) {
        networkPersonageService(PERSONAGE_LIST_URL).getPersonage()
                .enqueue(object : Callback<PersonageOpen> {
                    override fun onResponse(
                            call: Call<PersonageOpen>,
                            response: Response<PersonageOpen>) {
                        response.body()?.let { result ->
                            for (i in result.personageList) {
                                val personageId = i.personageId
                                val personageImage = i.personageImage
                                val personageName = i.personageName
                                val personageSpecies = i.personageSpecies
                                val personageStatus = i.personageStatus
                                val personageGender = i.personageGender
                                val personageOrigin = i.personageOrigin
                                val personageLocation = i.personageLocation
                                val personageEpisode = i.personageEpisode
                                personageData.add(Personage(
                                        personageId,
                                        personageImage,
                                        personageName,
                                        personageSpecies,
                                        personageStatus,
                                        personageGender,
                                        personageOrigin,
                                        personageLocation,
                                        personageEpisode
                                ))
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


    override fun getLocationArray(executor: Executor, callback: (result: SuccessList<ArrayList<Location>>) -> Unit) {
        networkLocationService(LOCATION_LIST_URL).getLocation()
                .enqueue(object : Callback<LocationOpen> {
                    override fun onResponse(
                            call: Call<LocationOpen>,
                            response: Response<LocationOpen>) {
                        response.body()?.let { result ->
                            for (i in result.locationList) {
                                val locationId = i.locationId
                                val locationName = i.locationName
                                val locationType = i.locationType
                                val locationDimension = i.locationDimension
                                val locationUrl = i.locationUrl
                                val locationResidents = i.locationResidents
                                locationData.add(Location(locationId, locationName, locationType, locationDimension, locationUrl, locationResidents))
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

    override fun getEpisodeArray(executor: Executor, callback: (result: SuccessList<ArrayList<Episode>>) -> Unit) {

        networkEpisodeService(EPISODE_LIST_URL).getEpisode()
                .enqueue(object : Callback<EpisodeOpen> {
                    override fun onResponse(
                            call: Call<EpisodeOpen>,
                            response: Response<EpisodeOpen>) {
                        response.body()?.let { result ->
                            for (i in result.episodeList) {
                                val episodeId = i.episodeId
                                val episodeName = i.episodeName
                                val episode = i.episode
                                val episodeAirFate = i.episodeAirFate
                                episodeData.add(Episode(episodeId, episodeName, episode, episodeAirFate))
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

    private fun networkPersonageService(urlPersonage: String): OpenPersonageListApi {
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
        return retrofit.create(OpenPersonageListApi::class.java)
    }

    private fun networkLocationService(urlLocation: String): OpenLocationListApi {
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
        return retrofit.create(OpenLocationListApi::class.java)
    }

    private fun networkEpisodeService(urlEpisode: String): OpenEpisodeListApi {
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
        return retrofit.create(OpenEpisodeListApi::class.java)
    }
}

data class SuccessList<T>(val value: T)



package com.example.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.Episode
import com.example.demo.domain.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class PersonageDetailsViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var _personageLiveData = MutableLiveData<Personage>()
    private var _episodeLiveData = MutableLiveData<ArrayList<Episode>>()
    val personageLiveData: LiveData<Personage> = _personageLiveData
    val episodeLiveData: LiveData<ArrayList<Episode>> = _episodeLiveData

    fun getPersonage(id: Int, episode: ArrayList<String>) {
        myRep.getPersonageDetails(id, executors) {
            val result: Personage = it.value
            _personageLiveData.value = result
        }
        myRep.getEpisodeForRV(episode, executors) {
            val result: ArrayList<Episode> = it.value
            _episodeLiveData.value = result
        }
    }
}
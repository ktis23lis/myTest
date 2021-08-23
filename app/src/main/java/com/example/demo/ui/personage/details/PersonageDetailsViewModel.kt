package com.example.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class PersonageDetailsViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var _personageLiveData = MutableLiveData<Personage>()
    private var _episodeLiveData = MutableLiveData<ArrayList<Episode>>()
    val personageLiveData: LiveData<Personage> = _personageLiveData
    val episodeLiveData: LiveData<ArrayList<Episode>> = _episodeLiveData

    fun getPersonage(id: Int) {
        myRep.getPersonageDetails(id, executors) {
            val result: Personage = it.value
            _personageLiveData.value = result
            myRep.getEpisodeForRV(result.personageEpisode, executors) {
                val result: ArrayList<Episode> = it.value
                _episodeLiveData.value = result
            }
        }
    }
}
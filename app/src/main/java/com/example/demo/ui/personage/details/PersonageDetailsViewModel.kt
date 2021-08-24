package com.example.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class PersonageDetailsViewModel : ViewModel() {

    private var myId = 0
    private var myRep = RepositoryRetrofitDetails()
    private var resultArray = ArrayList<Episode>()
    private val executors = Executors.newCachedThreadPool()
    private var _personageLiveData = MutableLiveData<Personage>()
    private var _episodeLiveData = MutableLiveData<ArrayList<Episode>>()
    val personageLiveData: LiveData<Personage> = _personageLiveData
    val episodeLiveData: LiveData<ArrayList<Episode>> = _episodeLiveData

    fun getPersonage(id: Int) {
        myRep.getPersonageDetails(id, executors) { personage ->
            val result: Personage = personage.value
            _personageLiveData.value = result
            for (i in result.personageEpisode) {
                myId = (i.substring(i.lastIndexOf("/") + 1)).toInt()
                myRep.getEpisodeDetails(myId, executors) {
                    resultArray.add(it.value)
                    _episodeLiveData.value = resultArray
                }
            }
        }
    }
}


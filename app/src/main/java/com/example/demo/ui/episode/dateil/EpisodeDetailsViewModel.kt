package com.example.demo.ui.episode.dateil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class EpisodeDetailsViewModel : ViewModel() {

    private var myId = 0
    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var resultArray =  ArrayList<Personage> ()
    private var _episodeLiveData = MutableLiveData<Episode>()
    private var _personageLiveData = MutableLiveData<ArrayList<Personage>>()
    val episodeLiveData: LiveData<Episode> = _episodeLiveData
    val personageLiveData: LiveData<ArrayList<Personage>> = _personageLiveData

    fun getPersonage(id: Int) {
        myRep.getEpisodeDetails(id, executors) { episode ->
            val result: Episode = episode.value
            _episodeLiveData.value = result
            for (i in result.episodeCharacters){
                myId = (i.substring(i.lastIndexOf("/") + 1)).toInt()
                myRep.getPersonageDetails(myId, executors){
                    resultArray.add(it.value)
                    _personageLiveData.value = resultArray
                }
            }
        }
    }
}

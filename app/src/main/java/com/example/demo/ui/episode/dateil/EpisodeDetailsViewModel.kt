package com.example.demo.ui.episode.dateil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class EpisodeDetailsViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var _episodeLiveData = MutableLiveData<Episode>()
    private var _personageLiveData = MutableLiveData<ArrayList<Personage>>()
    val episodeLiveData: LiveData<Episode> = _episodeLiveData
    val personageLiveData: LiveData<ArrayList<Personage>> = _personageLiveData

    fun getPersonage(id: Int) {
        myRep.getEpisodeDetails(id, executors) {
            val result: Episode = it.value
            _episodeLiveData.value = result
            myRep.getPersonageForRV(result.episodeCharacters, executors) {
                val result: ArrayList<Personage> = it.value
                _personageLiveData.value = result
            }
        }
    }
}
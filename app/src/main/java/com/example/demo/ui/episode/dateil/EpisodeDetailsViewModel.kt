package com.example.demo.ui.episode.dateil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.Episode
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class EpisodeDetailsViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var _episodeLiveData = MutableLiveData<Episode>()
    val episodeLiveData: LiveData<Episode> = _episodeLiveData

    fun getPersonage(id: Int) {
        myRep.getEpisodeDetails(id, executors) {
            val result: Episode = it.value
            _episodeLiveData.value = result
        }
    }
}
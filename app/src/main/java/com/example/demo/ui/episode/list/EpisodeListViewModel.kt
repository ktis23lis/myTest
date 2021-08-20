package com.example.demo.ui.episode.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.Episode
import com.example.demo.domain.repository.RepositoryListRetrofitList
import java.util.concurrent.Executors

class EpisodeListViewModel : ViewModel() {

    private var myRep = RepositoryListRetrofitList()
    private val executors = Executors.newCachedThreadPool()
    private var _episodeLiveData = MutableLiveData<ArrayList<Episode>>()
    private val _loadingLiveData = MutableLiveData(false)
    val episodeLiveData: LiveData<ArrayList<Episode>> = _episodeLiveData
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getEpisode() {
        _loadingLiveData.value = true
        myRep.getEpisodeArray(executors) {
            val result: ArrayList<Episode> = it.value
            _episodeLiveData.value = result
            _loadingLiveData.value = false
        }
    }
}

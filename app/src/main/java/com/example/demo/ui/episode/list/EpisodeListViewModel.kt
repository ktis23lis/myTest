package com.example.demo.ui.episode.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Episode
import com.example.demo.domain.repository.RepositoryRetrofitList
import java.util.concurrent.Executors

class EpisodeListViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitList()
    private var _episodeLiveData = MutableLiveData<ArrayList<Episode>>()
    private val _loadingLiveData = MutableLiveData(false)
    val episodeLiveData: LiveData<ArrayList<Episode>> = _episodeLiveData
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getEpisode() {
        _loadingLiveData.value = true
        myRep.getEpisodeArray() {
            val result: ArrayList<Episode> = it.value
            _episodeLiveData.value = result
            _loadingLiveData.value = false
        }
    }
}

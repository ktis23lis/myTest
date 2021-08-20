package com.example.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.Personage
import com.example.demo.domain.repository.RepositoryListRetrofitList
import java.util.concurrent.Executors

class PersonageListViewModel : ViewModel() {

    private var myRep = RepositoryListRetrofitList()
    private val executors = Executors.newCachedThreadPool()
    private var _personageLiveData = MutableLiveData<ArrayList<Personage>>()
    private val _loadingLiveData = MutableLiveData(false)
    val personageLiveData: LiveData<ArrayList<Personage>> = _personageLiveData
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getPersonage() {
        _loadingLiveData.value = true
        myRep.getPersonageArray(executors) {
            val result: ArrayList<Personage> = it.value
            _personageLiveData.value = result
            _loadingLiveData.value = false
        }
    }
}

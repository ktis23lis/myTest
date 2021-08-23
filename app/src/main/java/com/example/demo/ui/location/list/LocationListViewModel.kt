package com.example.demo.ui.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Location
import com.example.demo.domain.repository.RepositoryRetrofitList
import java.util.concurrent.Executors

class LocationListViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitList()
    private val executors = Executors.newCachedThreadPool()
    private var _locationLiveData = MutableLiveData<ArrayList<Location>>()
    private val _loadingLiveData = MutableLiveData(false)
    val locationLiveData: LiveData<ArrayList<Location>> = _locationLiveData
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getLocation() {
        _loadingLiveData.value = true
        myRep.getLocationArray(executors) {
            val result: ArrayList<Location> = it.value
            _locationLiveData.value = result
            _loadingLiveData.value = false
        }
    }
}

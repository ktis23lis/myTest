package com.example.demo.ui.location.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Episode
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class LocationDetailsViewModel : ViewModel() {

    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var _locationLiveData = MutableLiveData<Location>()
    private var _personageLiveData = MutableLiveData<ArrayList<Personage>>()
    val locationLiveData: LiveData<Location> = _locationLiveData
    val personageLiveData: LiveData<ArrayList<Personage>> = _personageLiveData

    fun getLocation(id: Int) {
        myRep.getLocationDetails(id, executors) {
            val result: Location = it.value
            _locationLiveData.value = result
            myRep.getPersonageForRV(result.locationResidents, executors) {
                val result: ArrayList<Personage> = it.value
                _personageLiveData.value = result
            }
        }

    }



}
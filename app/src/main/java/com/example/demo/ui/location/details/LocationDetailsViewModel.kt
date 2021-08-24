package com.example.demo.ui.location.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.domain.model.Location
import com.example.demo.domain.model.Personage
import com.example.demo.domain.repository.RepositoryRetrofitDetails
import java.util.concurrent.Executors

class LocationDetailsViewModel : ViewModel() {

    private var myId = 0
    private var myRep = RepositoryRetrofitDetails()
    private val executors = Executors.newCachedThreadPool()
    private var _locationLiveData = MutableLiveData<Location>()
    private var _personageLiveData = MutableLiveData<ArrayList<Personage>>()
    val locationLiveData: LiveData<Location> = _locationLiveData
    val personageLiveData: LiveData<ArrayList<Personage>> = _personageLiveData
    private var resultArray = ArrayList<Personage>()

    fun getLocation(id: Int) {
        myRep.getLocationDetails(id, executors) { location ->
            val result: Location = location.value
            _locationLiveData.value = result
            for (i in result.locationResidents) {
                myId = (i.substring(i.lastIndexOf("/") + 1)).toInt()
                myRep.getPersonageDetails(myId, executors) {
                    resultArray.add(it.value)
                    _personageLiveData.value = resultArray
                }
            }
        }
    }
}
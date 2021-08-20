package com.example.demo.network.responses

import android.os.Parcelable
import com.example.demo.domain.Location
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationOpen(
        @SerializedName("results")
        val locationList: ArrayList<Location>)
    : Parcelable
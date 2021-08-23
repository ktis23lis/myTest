package com.example.demo.network.responses

import android.os.Parcelable
import com.example.demo.domain.model.Personage
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonageOpen(
        @SerializedName("results")
        val personageList: ArrayList<Personage>)
    : Parcelable
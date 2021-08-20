package com.example.demo.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Episode (
        @SerializedName("id")
        val episodeId: Int,
        @SerializedName("name")
        val episodeName : String,
        @SerializedName("episode")
        val episode : String,
        @SerializedName("air_date")
        val episodeAirFate : String)
    : Parcelable
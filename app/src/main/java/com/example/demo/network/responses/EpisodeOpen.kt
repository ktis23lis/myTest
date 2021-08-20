package com.example.demo.network.responses

import android.os.Parcelable
import com.example.demo.domain.Episode
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeOpen(
        @SerializedName("results")
        val episodeList: ArrayList<Episode>)
    : Parcelable

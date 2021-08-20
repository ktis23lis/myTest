package com.example.demo.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Personage(
        @SerializedName("id")
        val personageId: Int,
        @SerializedName("image")
        val personageImage: String,
        @SerializedName("name")
        val personageName: String,
        @SerializedName("species")
        val personageSpecies: String,
        @SerializedName("status")
        val personageStatus: String,
        @SerializedName("gender")
        val personageGender: String,
        @SerializedName("origin")
        val personageOrigin : Origin,
        @SerializedName("location")
        val personageLocation : Location,
        @SerializedName("episode")
        val personageEpisode : ArrayList<String>
) : Parcelable

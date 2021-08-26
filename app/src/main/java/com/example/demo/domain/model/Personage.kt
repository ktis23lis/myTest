package com.example.demo.domain.model

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
        val personagePersonageOrigin : PersonageOrigin,
    @SerializedName("location")
        val personageLocation : PersonageLocation,
    @SerializedName("episode")
        val personageEpisode : ArrayList<String>
) : Parcelable

@Parcelize
data class PersonageLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable

@Parcelize
data class PersonageOrigin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Parcelable


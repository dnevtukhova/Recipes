package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes: String,
    @SerializedName("image") val image: String,
    @SerializedName("servings") val servings: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("dishTypes") val dishTypes: MutableList<String>
): Parcelable
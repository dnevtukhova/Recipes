package com.dnevtukhova.recipes.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("dishTypes") val dishTypes: MutableList<String>
): Parcelable
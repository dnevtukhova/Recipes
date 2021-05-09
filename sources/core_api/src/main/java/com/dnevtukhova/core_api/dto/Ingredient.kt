package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.android.parcel.Parcelize
data class Ingredient(
    @SerializedName("amount") val amount: Amount,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String
) : Parcelable

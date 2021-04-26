package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NutritionItem (
    @SerializedName("title") val title: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("indented") val indented: Boolean,
    @SerializedName("percentOfDailyNeeds") val percentOfDailyNeeds: Float
): Parcelable
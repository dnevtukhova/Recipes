package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class NutritionWidget(
    @SerializedName("calories") val calories: String,
    @SerializedName("carbs") val carbs: String,
    @SerializedName("fat") val fat: String,
    @SerializedName("protein") val protein: String,
    @SerializedName("bad") val bad: List<NutritionItemDto>,
    @SerializedName("good") val good: List<NutritionItemDto>
) : Parcelable

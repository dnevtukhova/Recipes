package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MetricItem(
    @SerializedName("unit") val unit: String,
    @SerializedName("value") val value: Float
) : Parcelable

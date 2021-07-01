package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Amount(
    @SerializedName("metric") val metric: MetricItem,
    @SerializedName("us") val us: MetricItem,
) : Parcelable

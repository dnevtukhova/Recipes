package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Instructions (
    @SerializedName("") val instructions: List<Instruction>
    ) : Parcelable
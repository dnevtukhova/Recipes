package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data
class Instruction(
    @SerializedName("name") val name: String,
    @SerializedName("steps") val steps: List<Step>
) : Parcelable

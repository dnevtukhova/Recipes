package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class RecipesList (
    @SerializedName("recipes")val  recipes: List<Recipe>
): Parcelable

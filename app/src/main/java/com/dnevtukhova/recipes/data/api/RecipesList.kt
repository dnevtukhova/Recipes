package com.dnevtukhova.recipes.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class RecipesList (
    @SerializedName("recipes")val  recipes: MutableList<Recipe>
): Parcelable

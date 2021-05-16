package com.dnevtukhova.core_api.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "RECIPES_TABLE",
    indices = [Index(value = ["id", "checked"])])
@Parcelize
data class Recipe (
    @PrimaryKey
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes: String,
    @SerializedName("image") val image: String,
    @SerializedName("servings") val servings: String,
    @SerializedName("summary") val summary: String,
    @IgnoredOnParcel
    var checked: Int = 0
): Parcelable {
    @Ignore
    @SerializedName("dishTypes") val dishTypes: MutableList<String> = mutableListOf()
}

package com.dnevtukhova.recipes.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    @GET("/recipes/random")
    suspend fun getPopularRecipesList(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): RecipesList
}
package com.dnevtukhova.core_api.network

import com.dnevtukhova.core_api.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {
    @GET("/recipes/random")
    suspend fun getPopularRecipesList(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): RecipesList

    @GET("/recipes/{id}/nutritionWidget.json")
    suspend fun getRecipesNutritionWidget(
        @Path("id") id: Long,
        @Query("apiKey") apiKey: String
    ): NutritionWidget

    @GET("/recipes/{id}/ingredientWidget.json")
    suspend fun getIngredients(
        @Path("id") id: Long,
        @Query("apiKey") apiKey: String
    ):Ingredients

    @GET("/recipes/{id}/analyzedInstructions")
    suspend fun getStepsOfCooking(
        @Path("id") id: Long,
        @Query("apiKey") apiKey: String
    ): List<Instruction>
}
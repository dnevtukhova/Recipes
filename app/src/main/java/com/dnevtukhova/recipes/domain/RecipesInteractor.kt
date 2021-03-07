package com.dnevtukhova.recipes.domain

import com.dnevtukhova.recipes.data.api.NetworkConstants.API_KEY
import com.dnevtukhova.recipes.data.api.Recipe
import com.dnevtukhova.recipes.data.api.ServerApi

class RecipesInteractor constructor(private val api: ServerApi) {
    sealed class Result {
        data class Success(val data: List<Recipe>) : Result()
        data class Error(val error: Throwable) : Result()
    }

    suspend fun getPopularRecipesList(): Result {
        return try {
            val recipesList = api.getPopularRecipesList(apiKey = API_KEY, number = 10)
            Result.Success(recipesList.recipes)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}
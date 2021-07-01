package com.dnevtukhova.recipeslist.domain

import com.dnevtukhova.core_api.database.RecipesDao
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.core_api.network.ServerApi
import com.dnevtukhova.core_api.network.NetworkConstants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecipesInteractor @Inject constructor(private val api: ServerApi, private val dao: RecipesDao) {


    suspend fun getPopularRecipesList(): Flow<State> = flow {
        emit(api.getPopularRecipesList(apiKey = API_KEY, number = 10))
    }
        .flowOn(Dispatchers.IO)
        .map { list -> State.Success(list.recipes) }
        .onStart<State> { emit(State.Loading) }
        .catch { ex -> emit(State.Error(ex)) }
        .flowOn(Dispatchers.Default)

    suspend fun insertRecipeInDB(recipe: Recipe) {
        dao.createRecipe(recipe)
    }
}
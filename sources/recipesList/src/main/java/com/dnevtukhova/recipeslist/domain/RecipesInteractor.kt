package com.dnevtukhova.recipeslist.domain

import com.dnevtukhova.core_api.database.RecipesDao
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.core_api.dto.RecipesList
import com.dnevtukhova.core_api.network.NetworkConstants.API_KEY
import com.dnevtukhova.core_api.network.ServerApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class   RecipesInteractor @Inject constructor(
    private val api: ServerApi,
    private val dao: RecipesDao,
    private val dispatcher: CoroutineDispatcher
) {


    suspend fun getPopularRecipesList(): Flow<State> = flow {
        emit(api.getPopularRecipesList(apiKey = API_KEY, number = 10))
    }
        .flowOn(Dispatchers.IO)
        .map { list -> State.Success(list.recipes) }
       .onStart<State> { emit(State.Loading) }
     .catch { ex -> emit(State.Error(ex)) }
        .flowOn(dispatcher)

    suspend fun insertRecipeInDB(recipe: Recipe) {
        dao.createRecipe(recipe)
    }
}
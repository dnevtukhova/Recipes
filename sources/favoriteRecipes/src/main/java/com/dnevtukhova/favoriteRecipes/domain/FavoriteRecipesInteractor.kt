package com.dnevtukhova.favoriteRecipes.domain

import com.dnevtukhova.core_api.database.RecipesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FavoriteRecipesInteractor @Inject constructor(private val dao: RecipesDao) {

    suspend fun getFavoriteRecipesList(): Flow<State> =
        dao.getFavoriteRecipes()
            .flowOn(Dispatchers.IO)
            .map { State.Success(it) }
            .onStart<State> { emit(State.Loading) }
            .catch { ex -> emit(State.Error(ex)) }

    suspend fun deleteFromFavoriteList(recipeId: Long) {
        dao.deleteRecipe(recipeId)
    }
}
package com.dnevtukhova.recipes.domain

import com.dnevtukhova.recipes.data.api.NetworkConstants.API_KEY
import com.dnevtukhova.recipes.data.api.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecipesInteractor @Inject constructor(private val api: ServerApi) {


    suspend fun getPopularRecipesList(): Flow<State> = flow {
        emit(api.getPopularRecipesList(apiKey = API_KEY, number = 10))
    }
        .flowOn(Dispatchers.IO)
        .map { list -> State.Success(list.recipes) }
        .onStart<State> { emit(State.Loading) }
        .catch { ex -> emit(State.Error(ex)) }
        .flowOn(Dispatchers.Default)
}
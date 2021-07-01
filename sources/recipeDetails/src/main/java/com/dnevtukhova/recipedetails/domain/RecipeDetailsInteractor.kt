package com.dnevtukhova.recipedetails.domain

import com.dnevtukhova.core_api.network.NetworkConstants
import com.dnevtukhova.core_api.network.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecipeDetailsInteractor @Inject constructor(private val api: ServerApi) {
    suspend fun getRecipesNutritionWidget(recipeId: Long) = flow {
        emit(api.getRecipesNutritionWidget(apiKey = NetworkConstants.API_KEY, id = recipeId))
    }
        .flowOn(Dispatchers.IO)
        .map { it -> State.Success(it) }
        .onStart<State> { emit(State.Loading) }
        .catch { ex -> emit(State.Error(ex)) }
        .flowOn(Dispatchers.Default)

    suspend fun getIngredients (recipeId: Long): Flow<StateLoadIngredients> = flow {
        emit(api.getIngredients(apiKey = NetworkConstants.API_KEY, id = recipeId))
    }
    .flowOn(Dispatchers.IO)
    .map { it -> StateLoadIngredients.Success(it) }
    .onStart<StateLoadIngredients> { emit(StateLoadIngredients.Loading) }
    .catch { ex -> emit(StateLoadIngredients.Error(ex)) }
    .flowOn(Dispatchers.Default)

    suspend fun getInstructions (recipeId: Long): Flow<StateLoadInstructions> = flow {
        emit(api.getStepsOfCooking(apiKey = NetworkConstants.API_KEY, id = recipeId))
    }
        .flowOn(Dispatchers.IO)
        .map { it -> StateLoadInstructions.Success(it[0]) }
        .onStart<StateLoadInstructions> { emit(StateLoadInstructions.Loading) }
        .catch { ex -> emit(StateLoadInstructions.Error(ex)) }
        .flowOn(Dispatchers.Default)

}
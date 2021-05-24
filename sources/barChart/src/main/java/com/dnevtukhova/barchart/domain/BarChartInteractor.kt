package com.dnevtukhova.barchart.domain

import com.dnevtukhova.core_api.network.NetworkConstants
import com.dnevtukhova.core_api.network.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BarChartInteractor @Inject constructor(private val api: ServerApi) {

    suspend fun getRecipesNutritionWidget(recipeId: Long): Flow<State> = flow {
        emit(api.getRecipesNutritionWidget(apiKey = NetworkConstants.API_KEY, id = recipeId))
    }
        .flowOn(Dispatchers.IO)
        .map { it -> State.Success(it) }
        .onStart<State> { emit(State.Loading) }
        .catch { ex -> emit(State.Error(ex)) }
        .flowOn(Dispatchers.Default)
}
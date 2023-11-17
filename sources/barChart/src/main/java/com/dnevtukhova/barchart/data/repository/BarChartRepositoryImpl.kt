package com.dnevtukhova.barchart.data.repository

import com.dnevtukhova.barchart.data.converter.BarChartConverter
import com.dnevtukhova.barchart.domain.BarChartRepository
import com.dnevtukhova.barchart.domain.State
import com.dnevtukhova.core_api.network.NetworkConstants
import com.dnevtukhova.core_api.network.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class BarChartRepositoryImpl @Inject constructor(
    private val api: ServerApi,
    private val barChartConverter: BarChartConverter
) : BarChartRepository {

    override fun getRecipesNutritionWidget(recipeId: Long): Flow<State> = flow {
        emit(api.getRecipesNutritionWidget(apiKey = NetworkConstants.API_KEY, id = recipeId))
    }
        .flowOn(Dispatchers.IO)
        .map { state -> State.Success(barChartConverter.convert(state)) }
        .onStart<State> { emit(State.Loading) }
        .catch { ex -> emit(State.Error(ex)) }
}
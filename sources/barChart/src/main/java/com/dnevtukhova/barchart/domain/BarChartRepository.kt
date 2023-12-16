package com.dnevtukhova.barchart.domain

import kotlinx.coroutines.flow.Flow

interface BarChartRepository {
    fun getRecipesNutritionWidget(recipeId: Long): Flow<State>
}

package com.dnevtukhova.barchart.domain.usecase

import com.dnevtukhova.barchart.domain.BarChartRepository
import com.dnevtukhova.barchart.domain.State
import kotlinx.coroutines.flow.Flow

class GetNutritionWidgetDataUseCase(private val repository: BarChartRepository)
    {
    operator fun invoke(recipeId: Long): Flow<State> =
        repository.getRecipesNutritionWidget(recipeId)

}



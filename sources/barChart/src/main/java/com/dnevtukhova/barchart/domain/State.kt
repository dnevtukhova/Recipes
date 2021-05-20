package com.dnevtukhova.barchart.domain

import com.dnevtukhova.core_api.dto.NutritionWidget

sealed class
State {
    object Loading: State()
    data class Success(val data: NutritionWidget) : State()
    data class Error(val error: Throwable) : State()
}
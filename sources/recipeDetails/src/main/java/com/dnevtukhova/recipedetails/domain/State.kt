package com.dnevtukhova.recipedetails.domain

import com.dnevtukhova.core_api.dto.Ingredients
import com.dnevtukhova.core_api.dto.Instruction
import com.dnevtukhova.core_api.dto.Instructions
import com.dnevtukhova.core_api.dto.NutritionWidget

sealed class
State {
    object Loading : State()
    data class Success(val data: NutritionWidget) : State()
    data class Error(val error: Throwable) : State()
}

sealed class
StateLoadIngredients {
    object Loading : StateLoadIngredients()
    data class Success(val data: Ingredients) : StateLoadIngredients()
    data class Error(val error: Throwable) : StateLoadIngredients()
}

sealed class
StateLoadInstructions {
    object Loading : StateLoadInstructions()
    data class Success(val data: Instruction) : StateLoadInstructions()
    data class Error(val error: Throwable) : StateLoadInstructions()
}
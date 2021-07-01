package com.dnevtukhova.favoriteRecipes.domain

import com.dnevtukhova.core_api.dto.Recipe

sealed class
State {
    object Loading: State()
    data class Success(val data: List<Recipe>) : State()
    data class Error(val error: Throwable) : State()
}
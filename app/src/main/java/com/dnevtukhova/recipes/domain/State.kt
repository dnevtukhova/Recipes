package com.dnevtukhova.recipes.domain

import com.dnevtukhova.recipes.data.api.Recipe

sealed class State {
    object Loading: State()
    data class Success(val data: List<Recipe>) : State()
    data class Error(val error: Throwable) : State()
}
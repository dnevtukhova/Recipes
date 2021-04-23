package com.dnevtukhova.recipeslist.domain

sealed class State {
    object Loading: State()
    data class Success(val data: List<com.dnevtukhova.core_api.dto.Recipe>) : State()
    data class Error(val error: Throwable) : State()
}
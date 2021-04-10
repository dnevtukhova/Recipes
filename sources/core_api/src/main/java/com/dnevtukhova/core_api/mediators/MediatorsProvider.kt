package com.dnevtukhova.core_api.mediators

interface MediatorsProvider {
    fun provideMainMediator(): MainMediator
    fun provideFilmsListMediator(): RecipesListMediator
    fun provideFilmsDetailsMediator(): RecipeDetailsMediator
}
package com.dnevtukhova.core_api.database

interface DataBaseProvider {
    fun provideDatabase(): RecipesDatabaseContract

    fun recipesDao(): RecipesDao
}
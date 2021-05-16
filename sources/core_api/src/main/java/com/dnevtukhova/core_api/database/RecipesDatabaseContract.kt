package com.dnevtukhova.core_api.database

interface RecipesDatabaseContract {
    fun recipesDao(): RecipesDao
}
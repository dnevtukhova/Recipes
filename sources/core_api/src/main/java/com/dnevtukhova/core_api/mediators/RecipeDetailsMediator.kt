package com.dnevtukhova.core_api.mediators

import com.dnevtukhova.core_api.dto.Recipe
import com.github.terrakok.cicerone.androidx.FragmentScreen


interface RecipeDetailsMediator {
    fun startRecipeDetailsFragment(recipe: Recipe): FragmentScreen
}
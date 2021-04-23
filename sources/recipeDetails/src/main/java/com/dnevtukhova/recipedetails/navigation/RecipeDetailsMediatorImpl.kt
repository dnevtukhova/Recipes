package com.dnevtukhova.recipedetails.navigation

import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.core_api.mediators.RecipeDetailsMediator
import com.dnevtukhova.recipedetails.presentation.RecipeDetailsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RecipeDetailsMediatorImpl @Inject constructor() : RecipeDetailsMediator {
    override fun startRecipeDetailsFragment(recipe: Recipe): FragmentScreen {
        return FragmentScreen { RecipeDetailsFragment.getNewInstance(recipe) }
    }
}
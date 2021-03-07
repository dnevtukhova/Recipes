package com.dnevtukhova.recipes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.recipes.domain.RecipesInteractor

class RecipesListViewModelFactory(private val recipesInteractor: RecipesInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RecipesInteractor::class.java)
            .newInstance(recipesInteractor)
    }
}
package com.dnevtukhova.preloader.presentation

import androidx.lifecycle.ViewModel
import com.dnevtukhova.core_api.mediators.RecipesListMediator
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PreloaderViewModel @Inject
constructor(
    private val router: Router,
    private val recipesListMediator: RecipesListMediator
) : ViewModel() {
    suspend fun openRecipesListFragment() {
        delay(2000)
        router.newRootScreen(recipesListMediator.startRecipeListFragment())
    }
}
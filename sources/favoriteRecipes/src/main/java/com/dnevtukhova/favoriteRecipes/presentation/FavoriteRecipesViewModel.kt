package com.dnevtukhova.favoriteRecipes.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.core_api.mediators.RecipeDetailsMediator
import com.dnevtukhova.favoriteRecipes.R
import com.dnevtukhova.favoriteRecipes.domain.FavoriteRecipesInteractor
import com.dnevtukhova.favoriteRecipes.domain.State
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteRecipesViewModel @Inject
constructor(
    private val interactor: FavoriteRecipesInteractor,
    private val router: Router,
    private val recipeDetailsMediator: RecipeDetailsMediator
) : ViewModel() {
    companion object {
        const val TAG = "FavoriteRecipesViewModel"
    }

    private val favoriteRecipesListLiveData = MutableLiveData<List<Recipe>>()
    private val progressLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Int>()

    val favoriteRecipesList: LiveData<List<Recipe>>
        get() = favoriteRecipesListLiveData
    val progress: LiveData<Boolean>
        get() = progressLiveData
    val error: LiveData<Int>
        get() = errorLiveData

    fun getFavoriteRecipesList() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getFavoriteRecipesList().collect { result ->
                if (result is State.Loading) {
                    progressLiveData.postValue(true)
                }
                if (result is State.Success) {
                    progressLiveData.postValue(false)
                    favoriteRecipesListLiveData.postValue(result.data)
                }
                if (result is State.Error) {
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())
                }
            }
        }
    }

    fun deleteFromFavorite(recipeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.deleteFromFavoriteList(recipeId)
        }
    }

    fun openDetailRecipeFragment(recipe: Recipe) {
        router.navigateTo(recipeDetailsMediator.startRecipeDetailsFragment(recipe))
    }
}
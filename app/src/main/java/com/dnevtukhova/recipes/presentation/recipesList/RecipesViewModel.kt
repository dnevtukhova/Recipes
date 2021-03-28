package com.dnevtukhova.recipes.presentation.recipesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.recipes.R
import com.dnevtukhova.recipes.Screens.RecipeDetails
import com.dnevtukhova.recipes.data.api.Recipe
import com.dnevtukhova.recipes.domain.RecipesInteractor
import com.dnevtukhova.recipes.domain.State
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipesViewModel @Inject constructor(private val interactor: RecipesInteractor, private val router: Router) : ViewModel() {
    companion object {
        const val TAG = "RecipesViewModel"
    }

    init {
        getPopularRecipesList()
    }

    private val popularRecipesListLiveData = MutableLiveData<List<Recipe>>()
    private val errorLiveData = MutableLiveData<Int>()
    private val progressLiveData = MutableLiveData<Boolean>()


    val recipesList: LiveData<List<Recipe>>
        get() = popularRecipesListLiveData
    val error: LiveData<Int>
        get() = errorLiveData
    val progress: LiveData<Boolean>
        get() = progressLiveData

    fun getPopularRecipesList() {

        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPopularRecipesList().onEach{ result ->
                if(result is State.Loading) {
                    progressLiveData.postValue(true)
                }
                if (result is State.Success) {
                    Log.d(TAG, result.data.toString())
                    progressLiveData.postValue(false)
                    popularRecipesListLiveData.postValue(result.data)
                }
                if (result is State.Error) {
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())
                }
            }.launchIn(viewModelScope)
        }
    }
    fun openDetailRecipeFragment() {
        router.navigateTo(RecipeDetails())
    }
}
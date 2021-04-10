package com.dnevtukhova.recipeslist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.core_api.mediators.RecipeDetailsMediator
import com.dnevtukhova.recipeslist.R
import com.dnevtukhova.recipeslist.domain.RecipesInteractor
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipesViewModel @Inject constructor(private val interactor: RecipesInteractor, private val router: Router, private val recipeDetailsMediator: RecipeDetailsMediator) : ViewModel() {
    companion object {
        const val TAG = "RecipesViewModel"
    }

    init {
        getPopularRecipesList()
    }

    private val popularRecipesListLiveData = MutableLiveData<List<com.dnevtukhova.core_api.dto.Recipe>>()
    private val errorLiveData = MutableLiveData<Int>()
    private val progressLiveData = MutableLiveData<Boolean>()


    val recipesList: LiveData<List<com.dnevtukhova.core_api.dto.Recipe>>
        get() = popularRecipesListLiveData
    val error: LiveData<Int>
        get() = errorLiveData
    val progress: LiveData<Boolean>
        get() = progressLiveData

    fun getPopularRecipesList() {

        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPopularRecipesList().onEach{ result ->
                if(result is com.dnevtukhova.recipeslist.domain.State.Loading) {
                    progressLiveData.postValue(true)
                }
                if (result is com.dnevtukhova.recipeslist.domain.State.Success) {
                    Log.d(TAG, result.data.toString())
                    progressLiveData.postValue(false)
                    popularRecipesListLiveData.postValue(result.data)
                }
                if (result is com.dnevtukhova.recipeslist.domain.State.Error) {
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())
                }
            }.launchIn(viewModelScope)
        }
    }
    fun openDetailRecipeFragment() {
        router.navigateTo(recipeDetailsMediator.startRecipeDetailsFragment())
    }

}
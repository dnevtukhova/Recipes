package com.dnevtukhova.recipes.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.recipes.R
import com.dnevtukhova.recipes.data.api.Recipe
import com.dnevtukhova.recipes.domain.RecipesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesViewModel constructor(private val interactor: RecipesInteractor) : ViewModel() {
    companion object {
        const val TAG = "RecipesViewModel"
    }

    private val popularRecipesListLiveData = MutableLiveData<List<Recipe>>()
    private val errorLiveData = MutableLiveData<Int>()

    val recipesList: LiveData<List<Recipe>>
        get() = popularRecipesListLiveData

    val error: LiveData<Int>
        get() = errorLiveData

    fun getPopularRecipesList() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPopularRecipesList().also { result ->
                if (result is RecipesInteractor.Result.Success) {
                    Log.d(TAG, result.data.toString())
                    popularRecipesListLiveData.postValue(result.data)
                }
                if (result is RecipesInteractor.Result.Error) {
                    errorLiveData.postValue(R.string.error)
                }
            }
        }
    }
}
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
import javax.inject.Inject

class RecipesViewModel @Inject constructor(private val interactor: RecipesInteractor) : ViewModel() {
    companion object {
        const val TAG = "RecipesViewModel"
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
        progressLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPopularRecipesList().also { result ->
                if (result is RecipesInteractor.Result.Success) {
                    Log.d(TAG, result.data.toString())
                    progressLiveData.postValue(false)
                    popularRecipesListLiveData.postValue(result.data)
                }
                if (result is RecipesInteractor.Result.Error) {
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())
                }
            }
        }
    }
}
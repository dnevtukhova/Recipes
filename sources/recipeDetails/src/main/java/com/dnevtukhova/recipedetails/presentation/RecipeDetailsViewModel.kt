package com.dnevtukhova.recipedetails.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.core_api.dto.Ingredients
import com.dnevtukhova.core_api.dto.Instruction
import com.dnevtukhova.core_api.dto.Instructions
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.core_api.mediators.BarChartMediator
import com.dnevtukhova.recipedetails.R
import com.dnevtukhova.recipedetails.domain.RecipeDetailsInteractor
import com.dnevtukhova.recipedetails.domain.State
import com.dnevtukhova.recipedetails.domain.StateLoadIngredients
import com.dnevtukhova.recipedetails.domain.StateLoadInstructions
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(
    private val router: Router,
    private val barChartMediator: BarChartMediator,
    private val interactor: RecipeDetailsInteractor
) : ViewModel() {

    companion object {
        const val TAG = "RecipeDetailsViewModel"
    }

    private val caloriesLiveData = MutableLiveData<String>()
    private val errorLiveData = MutableLiveData<Int>()
    private val ingredientsLiveData = MutableLiveData<Ingredients>()
    private val listIngredientsVisibilityLiveData = MutableLiveData<Boolean>()
    private val listStepsVisibilityLiveData = MutableLiveData<Boolean>()
    private val progressLiveData = MutableLiveData<Boolean>()
    private val stepsCookingLiveData = MutableLiveData<Instruction>()

    val calories: LiveData<String>
        get() = caloriesLiveData

    val error: LiveData<Int>
        get() = errorLiveData

    val ingredients: LiveData<Ingredients>
        get() = ingredientsLiveData

    val listIngredientsVisibility: LiveData<Boolean>
        get() = listIngredientsVisibilityLiveData

    val listStepsVisibility: LiveData<Boolean>
        get() = listStepsVisibilityLiveData

    val progress: LiveData<Boolean>
        get() = progressLiveData

    val stepsCooking: LiveData<Instruction>
    get() = stepsCookingLiveData

    fun getCalories(recipeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getRecipesNutritionWidget(recipeId).onEach { result ->
                if (result is State.Loading) {
                }
                if (result is State.Success) {
                    caloriesLiveData.postValue(result.data.calories)
                }
                if (result is State.Error) {
                    errorLiveData.postValue(R.string.error)
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getIngredients(recipeId: Long) {
        listIngredientsVisibilityLiveData.postValue(true)
        listStepsVisibilityLiveData.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getIngredients(recipeId).onEach { result ->
                if (result is StateLoadIngredients.Loading) {
                    progressLiveData.postValue(true)
                }
                if (result is StateLoadIngredients.Success) {
                    Log.d(TAG, result.data.toString())
                    progressLiveData.postValue(false)
                    ingredientsLiveData.postValue(result.data)
                }
                if (result is StateLoadIngredients.Error) {
                    progressLiveData.postValue(false)
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())

                }
            }.launchIn(viewModelScope)
        }
    }

    fun getSteps(recipeId: Long) {
        listStepsVisibilityLiveData.postValue(true)
        listIngredientsVisibilityLiveData.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getInstructions(recipeId).onEach { result ->
                if (result is StateLoadInstructions.Loading) {
                    progressLiveData.postValue(true)
                }
                if (result is StateLoadInstructions.Success) {
                    Log.d(TAG, result.data.toString())
                    progressLiveData.postValue(false)
                    stepsCookingLiveData.postValue(result.data)
                }
                if (result is StateLoadInstructions.Error) {
                    progressLiveData.postValue(false)
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())
                }
            }.launchIn(viewModelScope)
        }
    }

    fun openBarChartFragment(recipeId: Long) {
        router.navigateTo(barChartMediator.startBarChartFragment(idRecipe = recipeId))
    }

    fun insertRecipeinDB(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.insertRecipeInDB(recipe)
        }
    }
}
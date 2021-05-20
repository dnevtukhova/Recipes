package com.dnevtukhova.barchart.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.barchart.R
import com.dnevtukhova.barchart.domain.BarChartInteractor
import com.dnevtukhova.barchart.domain.State
import com.dnevtukhova.core_api.dto.NutritionWidget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class BarChartViewModel @Inject constructor(private val interactor: BarChartInteractor) :
    ViewModel() {

    companion object {
        const val TAG = "BarChartViewModel"
    }

    private val nutritionWidgetLiveData = MutableLiveData<NutritionWidget>()
    private val progressLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Int>()

    val nutritionWidget: LiveData<NutritionWidget>
        get() = nutritionWidgetLiveData
    val progress: LiveData<Boolean>
        get() = progressLiveData
    val error: LiveData<Int>
        get() = errorLiveData

    fun getNutritionWidget(recipeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getRecipesNutritionWidget(recipeId).onEach { result ->
                if (result is State.Loading) {
                    progressLiveData.postValue(true)
                }
                if (result is State.Success) {
                    Log.d(TAG, result.data.toString())
                    progressLiveData.postValue(false)
                    nutritionWidgetLiveData.postValue(result.data)
                }
                if (result is State.Error) {
                    errorLiveData.postValue(R.string.error)
                    Log.d(TAG, result.error.stackTraceToString())
                }
            }.launchIn(viewModelScope)
        }
    }
}
package com.dnevtukhova.barchart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnevtukhova.barchart.data.repository.BarChartRepositoryImpl
import com.dnevtukhova.barchart.domain.BarChartRepository
import com.dnevtukhova.barchart.domain.State
import com.dnevtukhova.barchart.domain.usecase.GetNutritionWidgetDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class BarChartViewModel @Inject constructor(private val getNutritionWidgetDataUseCase: GetNutritionWidgetDataUseCase) :
    ViewModel() {

    companion object {
        const val TAG = "BarChartViewModel"
    }

    private val _nutritionWidgetStateFlow = MutableStateFlow<State>(State.Loading)

    val nutritionWidgetStateFlow = _nutritionWidgetStateFlow.asStateFlow()

    fun getNutritionWidget(recipeId: Long) {
        getNutritionWidgetDataUseCase(recipeId).onEach { result ->
            when (result) {
                is State.Error -> _nutritionWidgetStateFlow.value = State.Error(result.error)
                is State.Loading -> _nutritionWidgetStateFlow.value = State.Loading
                is State.Success -> _nutritionWidgetStateFlow.value = State.Success(result.data)
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
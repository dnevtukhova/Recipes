package com.dnevtukhova.barchart.domain

import com.dnevtukhova.barchart.domain.model.BarChartModel

sealed class State {
    object Loading : State()
    data class Success(val data: BarChartModel) : State()
    data class Error(val error: Throwable) : State()
}
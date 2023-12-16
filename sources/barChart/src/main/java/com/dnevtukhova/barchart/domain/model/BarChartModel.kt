package com.dnevtukhova.barchart.domain.model

data class BarChartModel(
    val calories: String,
    val carbs: String,
    val fat: String,
    val protein: String,
    val bad: List<NutritionItem>,
    val good: List<NutritionItem>
)

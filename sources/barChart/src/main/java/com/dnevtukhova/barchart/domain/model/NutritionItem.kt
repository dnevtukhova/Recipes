package com.dnevtukhova.barchart.domain.model

data class NutritionItem(
    val title: String,
    val amount: String,
    val indented: Boolean,
    val percentOfDailyNeeds: Float
)

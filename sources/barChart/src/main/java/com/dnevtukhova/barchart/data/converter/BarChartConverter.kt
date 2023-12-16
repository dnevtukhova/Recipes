package com.dnevtukhova.barchart.data.converter

import com.dnevtukhova.barchart.domain.model.BarChartModel
import com.dnevtukhova.core_api.dto.NutritionWidget
import javax.inject.Inject

class BarChartConverter @Inject constructor(private val nutritionItemConverter: NutritionItemConverter) {

    fun convert(nutritionWidget: NutritionWidget): BarChartModel {
        return BarChartModel(
            calories = nutritionWidget.calories,
            carbs = nutritionWidget.carbs,
            fat = nutritionWidget.fat,
            protein = nutritionWidget.protein,
            bad = nutritionWidget.bad.map { nutritionItemConverter.convert(it) },
            good = nutritionWidget.good.map { nutritionItemConverter.convert(it) }
        )
    }
}
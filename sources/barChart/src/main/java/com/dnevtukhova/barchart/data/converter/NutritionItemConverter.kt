package com.dnevtukhova.barchart.data.converter

import com.dnevtukhova.barchart.domain.model.NutritionItem
import com.dnevtukhova.core_api.dto.NutritionItemDto

class NutritionItemConverter {
    fun convert(nutritionItemDto: NutritionItemDto): NutritionItem {
        return NutritionItem(
            nutritionItemDto.title,
            nutritionItemDto.amount,
            nutritionItemDto.indented,
            nutritionItemDto.percentOfDailyNeeds
        )
    }
}
package com.dnevtukhova.recipedetails.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dnevtukhova.core_api.dto.Ingredient
import com.dnevtukhova.recipedetails.R
import com.dnevtukhova.recipedetails.domain.NetworkConstants.URL_LOAD_PICTURE

@Composable
fun RecipeDetailsIngredients(viewModel: RecipeDetailsViewModel) {
    val ingredients by viewModel.ingredients.observeAsState()
    ingredients?.let {
        RecipeDetailIngredientsContent(ingredients = it.ingredients)
    }
}

@Composable
fun RecipeDetailIngredientsContent(ingredients: List<Ingredient>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(ingredients) { ingredient ->
            RecipeDetailsIngredientItem(ingredient = ingredient)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeDetailsIngredientItem(ingredient: Ingredient) {
    val modifierWithPadding = Modifier.padding(start = 20.dp)
    Column {
        GlideImage(
            model = "$URL_LOAD_PICTURE${ingredient.image}",
            contentDescription = null,
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp)
                .padding(20.dp)
        )
        Text(
            text = ingredient.name,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_regular,
                    weight = FontWeight(400),
                    style = FontStyle.Normal
                )
            ),
            modifier = modifierWithPadding
        )
        Text(
            text = "${ingredient.amount.metric.value} ${ingredient.amount.metric.unit}",
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_regular,
                    weight = FontWeight(400),
                    style = FontStyle.Normal
                )
            ),
            modifier = modifierWithPadding
        )
    }
}
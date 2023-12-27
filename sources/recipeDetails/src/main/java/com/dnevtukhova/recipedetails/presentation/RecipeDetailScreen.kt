package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.dnevtukhova.recipedetails.R

@Composable
fun RecipeDetailScreen(recipeDetailViewModel: RecipeDetailsViewModel) {
    RecipeDetailScreenContent(recipeDetailViewModel = recipeDetailViewModel)
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMotionApi::class)
@Composable
fun RecipeDetailScreenContent(recipeDetailViewModel: RecipeDetailsViewModel) {
    val calories by recipeDetailViewModel.calories.observeAsState()
    val currentRecipe by recipeDetailViewModel.currentRecipeStateFlow.collectAsState()
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.recipe_details_motion_scene)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in a String format
    }

    val maxPx = with(LocalDensity.current) { 270.dp.roundToPx().toFloat() }
    val minPx = with(LocalDensity.current) { 70.dp.roundToPx().toFloat() }
    val toolbarHeight = remember { mutableStateOf(maxPx) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val height = toolbarHeight.value

                if (height + available.y > maxPx) {
                    toolbarHeight.value = maxPx
                    return Offset(0f, maxPx - height)
                }

                if (height + available.y < minPx) {
                    toolbarHeight.value = minPx
                    return Offset(0f, minPx - height)
                }

                toolbarHeight.value += available.y
                return Offset(0f, available.y)
            }

        }
    }

    val progress = 1 - (toolbarHeight.value - minPx) / (maxPx - minPx)

    MotionLayout(
        motionScene = MotionScene(motionScene),
        progress = progress,
        modifier = Modifier
    ) {

        RecipeDetailTitle(currentRecipe?.title ?: "empty string")

        TopInformationCookingItem(calories = calories, currentRecipe = currentRecipe)

        ImageOfRecipe(currentRecipe, recipeDetailViewModel::onCurrentRecipeChange)

        RecipeDetailsTabContent(recipeDetailViewModel, nestedScrollConnection)
    }
}
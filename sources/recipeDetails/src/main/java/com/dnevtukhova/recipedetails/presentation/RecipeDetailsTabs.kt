package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.layoutId
import com.dnevtukhova.recipedetails.R

@SuppressLint("SuspiciousIndentation")
@Composable
fun RecipeDetailsTabContent(
    viewModel: RecipeDetailsViewModel,
    nestedScrollConnection: NestedScrollConnection
) {

    val tabIndex by viewModel.tabIndicatorStateFlow.collectAsState()
    val currentRecipe by viewModel.currentRecipeStateFlow.collectAsState()

    val tabs = listOf(
        stringResource(id = R.string.tabs_ingredients),
        stringResource(id = R.string.tabs_steps)
    )

    TabRow(selectedTabIndex = tabIndex,
        backgroundColor = colorResource(id = R.color.orange_light_light),
        contentColor = colorResource(id = R.color.white),
        modifier = Modifier.layoutId("tab_layout"),
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                height = 3.dp,
                color = colorResource(id = R.color.orange_dark)
            )
        }) {

        tabs.forEachIndexed { index, title ->
            Tab(text = { Text(title) },
                selected = tabIndex == index,
                onClick = {
                    viewModel.onTabIndicatorChange(index)
                    if (index == 0) {
                        viewModel.getIngredients(recipeId = currentRecipe?.id ?: 0)
                    } else {
                        viewModel.getSteps(recipeId = currentRecipe?.id ?: 0)
                    }
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .nestedScroll(nestedScrollConnection)
            .layoutId("tab_content")
    ) {
        when (tabIndex) {

            0 -> RecipeDetailsIngredients(viewModel)
            1 -> RecipeDetailsSteps(recipeDetailsViewModel = viewModel)
        }
    }
}

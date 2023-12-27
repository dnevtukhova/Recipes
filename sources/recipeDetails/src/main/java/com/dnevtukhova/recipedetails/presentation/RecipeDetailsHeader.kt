package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.layoutId
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.recipedetails.R


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageOfRecipe(
    currentRecipe: Recipe?,
    onLikeIconClick: (recipe: Recipe) -> Unit
) {
    GlideImage(
        model = currentRecipe?.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .layoutId("image_recipe")
    )

    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.color_circle),
                shape = CircleShape
            )
            .alpha(0.8f)
            .layoutId("circle")
    )

    Image(
        modifier = Modifier
            .clickable(
                enabled = true
            ) {
                onLikeIconClick(
                    currentRecipe!!.copy(
                        checked = if (currentRecipe.checked == 0) {
                            1
                        } else {
                            0
                        }
                    )
                )
            }
            .layoutId("like"),
        painter = if (currentRecipe?.checked == 1) {
            painterResource(R.drawable.ic_baseline_favorite_24)
        } else {
            painterResource(R.drawable.ic_baseline_favorite_border_24)
        },
        contentDescription = null,
    )
}

@Composable
fun TopInformationCookingItem(
    calories: String?,
    currentRecipe: Recipe?
) {
    Image(
        painter = painterResource(R.drawable.ic_baseline_query_builder_24),
        modifier = Modifier
            .layoutId("image_ready_in_minutes"),
        contentDescription = null
    )
    Text(
        text = "${currentRecipe?.readyInMinutes} min",
        modifier = Modifier
            .layoutId("ready_in_minutes_text"),
        fontFamily = FontFamily(Font(R.font.poppins_extra_light))
    )
    Image(
        painter = painterResource(R.drawable.ic_baseline_whatshot_24),
        modifier = Modifier
            .layoutId("image_kcal"),
        contentDescription = null
    )
    Text(
        text = "$calories kcal",
        modifier = Modifier
            // .padding(10.dp)
            .layoutId("kcal_text"),
        fontFamily = FontFamily(Font(R.font.poppins_extra_light))
    )
    Image(
        painter = painterResource(R.drawable.ic_baseline_room_service_24),
        modifier = Modifier
            // .padding(10.dp)
            //  .size(20.dp)
            .layoutId("image_servings"),
        contentDescription = null
    )
    Text(
        text = "${currentRecipe?.servings} servings",
        modifier = Modifier
            //  .padding(10.dp)
            .layoutId("servings_text"),
        fontFamily = FontFamily(Font(R.font.poppins_extra_light))
    )
}


@Composable
fun RecipeDetailTitle(title: String = "") {
    Text(
        text = title,
        modifier = Modifier
            .layoutId("title"),
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 20.sp,
        color = colorResource(id = R.color.dark_text),
    )
}
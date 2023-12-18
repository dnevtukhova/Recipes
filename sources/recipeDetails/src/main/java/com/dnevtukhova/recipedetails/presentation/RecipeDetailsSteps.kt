package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnevtukhova.core_api.dto.Step
import com.dnevtukhova.recipedetails.R

@Composable
fun RecipeDetailsSteps(recipeDetailsViewModel: RecipeDetailsViewModel) {
    val steps by recipeDetailsViewModel.stepsCooking.observeAsState()

    steps?.let {
        RecipeDetailsStepContent(steps = it.steps)
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailsStepContent(steps: List<Step>) {
    Scaffold {
        LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
            items(steps) { step ->
                RecipeDetailsStep(stepNumber = step.number, stepName = step.step)

            }
        }
    }

}

@Composable
fun RecipeDetailsStep(stepNumber: Int, stepName: String) {
    Column {
        Text(
            text = stepNumber.toString(),
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        Text(
            text = stepName,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun RecipeDetailsStepContentPreview() {
    MaterialTheme {
        RecipeDetailsStepContent(
            listOf(
                Step(
                    1,
                    " fhgh dgnrehkreh dsvnrsgjgrk gfvmesksrgjk dsgmdsgljdskgnsrkj"
                ), Step(2, " hello")
            )
        )
    }
}
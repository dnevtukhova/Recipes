package com.dnevtukhova.recipeslist.domain

import com.dnevtukhova.core_api.database.RecipesDao
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.core_api.dto.RecipesList
import com.dnevtukhova.core_api.network.NetworkConstants
import com.dnevtukhova.core_api.network.ServerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okio.IOException
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class RecipesInteractorTest {

    @ExperimentalCoroutinesApi
    val testDispatcher = TestCoroutineDispatcher()
    private val apiService = mock<ServerApi>()
    private val dao = mock<RecipesDao>()

    @ExperimentalCoroutinesApi
    val testrecipesInteractor =
        RecipesInteractor(api = apiService, dao = dao, dispatcher = testDispatcher)

    @ExperimentalCoroutinesApi
    @Test
    fun `flow emits successfully`() = runBlocking {
        val list: MutableList<Recipe> = mutableListOf()
        list.add(Recipe(1, "recipe", "1", "image", "2", "summary"))
        apiService.stub {
            onBlocking {
                getPopularRecipesList(
                    apiKey = NetworkConstants.API_KEY,
                    number = 10
                )
            } doReturn RecipesList(list)
        }

        // Test
        val flow = testrecipesInteractor.getPopularRecipesList()

        // Verify
        val t = launch {
            flow
                .collect { result ->
                    result.shouldBeEqualTo(State.Loading)
                    (result is State.Success).shouldBeTrue()
                    result.data shouldBeEqualTo list
                }
        }
        t.cancel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `flow emits with error`() = runBlockingTest {
        apiService.stub {
            onBlocking {
                getPopularRecipesList(
                    apiKey = NetworkConstants.API_KEY,
                    number = 10
                )
            } doAnswer {
                throw IOException()
            }

            val flow = testrecipesInteractor.getPopularRecipesList()

            val t = launch {
                flow.collect { result: State ->
                    result.shouldBeEqualTo(State.Loading)
                    (result is State.Error).shouldBeTrue()
                    result.error.shouldBeEqualTo(IOException())
                }
            }
            t.cancel()
        }
    }
}
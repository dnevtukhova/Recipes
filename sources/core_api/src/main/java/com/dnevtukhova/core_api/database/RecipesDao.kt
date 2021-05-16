package com.dnevtukhova.core_api.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnevtukhova.core_api.dto.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Query("SELECT * from RECIPES_TABLE where checked=1")
    fun getFavoriteRecipes(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createRecipe(recipe: Recipe)

    @Query("DELETE from recipes_table where id = :recipeId")
    suspend fun deleteRecipe(recipeId: Long)



}
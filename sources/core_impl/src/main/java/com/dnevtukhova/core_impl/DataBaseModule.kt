package com.dnevtukhova.core_impl

import android.content.Context
import androidx.room.Room
import com.dnevtukhova.core_api.database.RecipesDao
import com.dnevtukhova.core_api.database.RecipesDatabaseContract
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val RECIPES_DATABASE_NAME = "RECIPES_DB"

@Module
class DataBaseModule {
    @Provides
    @Reusable
    fun provideRecipesDao(recipesDatabaseContract: RecipesDatabaseContract): RecipesDao {
        return recipesDatabaseContract.recipesDao()
    }

    @Provides
    @Singleton
    fun provideRecipesDatabase(context: Context): RecipesDatabaseContract {
        return Room.databaseBuilder(
            context,
            RecipesDataBase::class.java, RECIPES_DATABASE_NAME
        ).build()
    }
}
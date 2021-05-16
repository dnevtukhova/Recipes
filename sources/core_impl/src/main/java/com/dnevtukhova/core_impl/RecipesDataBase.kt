package com.dnevtukhova.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dnevtukhova.core_api.database.RecipesDatabaseContract
import com.dnevtukhova.core_api.dto.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipesDataBase : RoomDatabase(), RecipesDatabaseContract
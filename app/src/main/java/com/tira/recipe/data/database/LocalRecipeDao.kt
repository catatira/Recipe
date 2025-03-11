package com.tira.recipe.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tira.recipe.common.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun removeRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    fun getRecipes(): Flow<List<Recipe>>
}

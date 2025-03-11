package com.tira.recipe.data.repository

import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.data.database.LocalRecipeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface LocalRecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun removeRecipe(recipe: Recipe)
}

class LocalRecipeRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val localRecipeDao: LocalRecipeDao,
) : LocalRecipeRepository {
    override fun getRecipes(): Flow<List<Recipe>> = localRecipeDao.getRecipes()

    override suspend fun insertRecipe(recipe: Recipe) = withContext(dispatcherProvider.io()) {
        localRecipeDao.insertRecipe(recipe)
    }

    override suspend fun removeRecipe(recipe: Recipe) = withContext(dispatcherProvider.io()) {
        localRecipeDao.removeRecipe(recipe)
    }
}

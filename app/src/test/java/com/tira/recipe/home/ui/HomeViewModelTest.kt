package com.tira.recipe.home.ui

import app.cash.turbine.test
import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.data.repository.LocalRecipeRepository
import com.tira.recipe.data.repository.OpenAiRecipeRepository
import com.tira.recipe.util.TestCoroutineRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var dispatchersProvider: DispatcherProvider
    private lateinit var openAiRecipeRepository: OpenAiRecipeRepository
    private lateinit var localRecipeRepository: LocalRecipeRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    val testDispatcherRule = TestCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        dispatchersProvider = mockk {
            every { default() } returns testDispatcherRule.dispatcher
        }
        openAiRecipeRepository = mockk()
        localRecipeRepository = mockk {
            every { getRecipes() } returns flowOf(emptyList())
        }
        viewModel =
            HomeViewModel(dispatchersProvider, openAiRecipeRepository, localRecipeRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `initial state should be Loading then Cleared`() = runTest(testDispatcherRule.dispatcher) {
        viewModel.homeState.test {
            assertEquals(HomeState.Loading, awaitItem())
            advanceTimeBy(1000)
            assertTrue(awaitItem() is HomeState.Cleared)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onInputTextChange should update inputText`() = runTest(testDispatcherRule.dispatcher) {
        viewModel.inputText.test {
            assertEquals("", awaitItem())
            viewModel.onInputTextChange("Pasta")
            assertEquals("Pasta", awaitItem())
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onSearchRecipe should fetch and update homeState`() =
        runTest(testDispatcherRule.dispatcher) {
            val mockRecipes = listOf(
                Recipe(
                    title = "Pasta",
                    isFavorited = false,
                    duration = "20. min",
                    ingredients = emptyList(),
                    instructions = emptyList()
                )
            )
            coEvery { openAiRecipeRepository.generateRecipes("Pasta") } returns mockRecipes

            viewModel.onInputTextChange("Pasta")
            viewModel.onSearchRecipe()

            viewModel.homeState.test {
                assertEquals(HomeState.Loading, awaitItem())
                assertEquals(HomeState.SearchResults(mockRecipes), awaitItem())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onSearchRecipe should return Error if no results`() =
        runTest(testDispatcherRule.dispatcher) {
            coEvery { openAiRecipeRepository.generateRecipes("Pasta") } returns emptyList()

            viewModel.onInputTextChange("Pasta")
            viewModel.onSearchRecipe()

            viewModel.homeState.test {
                assertEquals(HomeState.Loading, awaitItem())
                assertEquals(HomeState.Error, awaitItem())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleRecipeFavoriteSelection should add to favorites if not favorited`() =
        runTest(testDispatcherRule.dispatcher) {
            val recipe = Recipe(
                title = "Pasta",
                isFavorited = false,
                duration = "20. min",
                ingredients = emptyList(),
                instructions = emptyList()
            )
            coEvery { localRecipeRepository.insertRecipe(any()) } just Runs

            viewModel.toggleRecipeFavoriteSelection(recipe)
            testScheduler.advanceUntilIdle()

            coVerify { localRecipeRepository.insertRecipe(recipe.copy(isFavorited = true)) }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleRecipeFavoriteSelection should remove from favorites if favorited`() =
        runTest(testDispatcherRule.dispatcher) {
            val recipe = Recipe(
                title = "Pasta",
                isFavorited = true,
                duration = "20. min",
                ingredients = emptyList(),
                instructions = emptyList()
            )
            coEvery { localRecipeRepository.removeRecipe(any()) } just Runs

            viewModel.toggleRecipeFavoriteSelection(recipe)
            testScheduler.advanceUntilIdle()

            coVerify { localRecipeRepository.removeRecipe(recipe) }
        }
}

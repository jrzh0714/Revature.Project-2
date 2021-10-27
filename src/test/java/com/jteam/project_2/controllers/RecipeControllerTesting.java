package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.services.RecipeService;
import com.jteam.project_2.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTesting {

    private RecipeController testRecipeController;
    private RecipeService mockRecipeService;
    private UserService mockUserService;
    private Recipe mockRecipe;
    private List<Recipe> mockList;

    @BeforeEach
    public void init() {
        mockRecipeService = mock(RecipeService.class);
        mockUserService = mock(UserService.class);
        mockRecipe = mock(Recipe.class);
        mockList = mock(List.class);
        testRecipeController = new RecipeController(mockRecipeService, mockUserService);
    }

    @Test
    public void getRecipeTest() {
        when(mockRecipeService.getRecipeById(5)).thenReturn(mockRecipe);
        int testID = 5;
        Recipe testRecipe = testRecipeController.getRecipe(testID);
        assertSame(mockRecipe, testRecipe, "Correct Recipe not returned!");
    }

    @Test
    public void getRecipesByNameTest() {
        when(mockRecipeService.searchRecipesByName("test")).thenReturn(mockList);
        List<Recipe> testList = testRecipeController.getRecipesByName("test");
        assertSame(mockList, testList, "Correct List not returned!");
    }

    @Test
    public void getRecipesByStartingStringTest() {
        when(mockRecipeService.searchRecipesByStartingString("test")).thenReturn(mockList);
        List<Recipe> testList = testRecipeController.getRecipesByStartingString("test");
        assertSame(mockList, testList, "Correct List not returned!");
    }

    @Test
    public void getTrendingRecipesTest() {
        when(mockRecipeService.searchRecipesByTrending()).thenReturn(mockList);
        List<Recipe> testList = testRecipeController.getTrendingRecipes();
        assertSame(mockList, testList, "Correct List not returned!");
    }

    @Test
    public void submitRecipeTest() {
        when(mockRecipeService.save(mockRecipe)).thenReturn(mockRecipe);
        Recipe testRecipe = testRecipeController.submitRecipe(mockRecipe);
        verify(mockRecipeService).save(mockRecipe);
        assertSame(mockRecipe, testRecipe, "Correct Recipe not returned!");
    }

    @Test
    public void getRecipesAboveRatingTest() {
        double testRating = 5.0;
        when(mockRecipeService.getRecipesByRating(testRating)).thenReturn(mockList);
        List<Recipe> testList = testRecipeController.getRecipesAboveRating(testRating);
        assertSame(mockList, testList, "Correct List not returned!");
    }

    @Test
    public void getRecipesByStateTest() {
        String testState = "State";
        when(mockRecipeService.getRecipesByState(testState)).thenReturn(mockList);
        List<Recipe> testList = testRecipeController.getRecipesByState(testState);
        assertSame(mockList, testList, "Correct List not returned!");
    }

    @Test
    public void getRecipesByIngredientTest() {
        String testIngredient = "Ingredient";
        when(mockRecipeService.searchRecipesByIngredient(testIngredient)).thenReturn(mockList);
        List<Recipe> testList = testRecipeController.getRecipesByIngredient(testIngredient);
        assertSame(mockList, testList, "Correct List not returned!");
    }

    @Test
    public void editRecipeTest() {
        when(mockRecipeService.save(mockRecipe)).thenReturn(mockRecipe);
        Recipe testRecipe = testRecipeController.editRecipe(mockRecipe);
        verify(mockRecipeService).save(mockRecipe);
        assertSame(mockRecipe, testRecipe, "Correct Recipe not returned!");
    }

    @Test
    public void likeRecipeTest() {
        when(mockRecipeService.getRecipeById(5)).thenReturn(mockRecipe);
        when(mockRecipeService.likeRecipe(mockRecipe, 1)).thenReturn(mockRecipe);
        Recipe testRecipe = testRecipeController.likeRecipe(5, 5);
        verify(mockRecipeService).likeRecipe(mockRecipe, 1);
        assertSame(mockRecipe, testRecipe, "Correct Recipe not returned!");
    }
}

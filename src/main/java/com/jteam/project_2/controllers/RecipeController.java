package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "recipes")
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/containing/{containing}")
    public List<Recipe> getRecipesByName(@PathVariable String containing){
        return recipeService.searchRecipesByName(containing);
    }

    @GetMapping("/starting/{startingWith}")
    public List<Recipe> getRecipesByStartingString(@PathVariable String startingWith){
        return recipeService.searchRecipesByStartingString(startingWith);
    }

    @GetMapping("/trending")
    public List<Recipe> getTrendingRecipes(){
        return recipeService.searchRecipesByTrending();
    }

    @PostMapping("/submitRecipe")
    public Recipe submitRecipe(@RequestBody Recipe toSubmit){
        return recipeService.save(toSubmit);
    }

    @GetMapping("/byRating/{rating}")
    public List<Recipe> getRecipesAboveRating(@PathVariable double rating){
        return recipeService.getRecipesByRating(rating);
    }

    @GetMapping("/byState/{state}")
    public List<Recipe> getRecipesByState(@PathVariable String state){
        return recipeService.getRecipesByState(state);
    }

    @PostMapping("/byIngredient")
    public List<Recipe> getRecipesByIngredient(@RequestBody Ingredient ingredient){
        return recipeService.searchRecipesByIngredient(ingredient);
    }

    @PutMapping("/editRecipe")
    public Recipe editRecipe(@RequestBody Recipe recipe){
        return recipeService.save(recipe);
    }
}

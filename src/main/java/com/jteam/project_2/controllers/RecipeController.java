package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Ingredient;
import org.apache.commons.lang.SerializationUtils;
import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.Step;
import com.jteam.project_2.models.StepIngredient;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.RecipeService;
import com.jteam.project_2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "recipes")
public class RecipeController {
    private RecipeService recipeService;
    private UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
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

    @PostMapping(path="/submitRecipe",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe submitRecipe(@RequestBody Recipe toSubmit){
        System.out.println(toSubmit);
        List<Step> steps = toSubmit.getRecipeSteps();
        for (Step step : steps) {
            step.setRecipe(toSubmit);
        }
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

    @GetMapping("/byIngredient/{ingredient}")
    public List<Recipe> getRecipesByIngredient(@PathVariable String ingredient){
        return recipeService.searchRecipesByIngredient(ingredient);
    }

    @PutMapping("/editRecipe")
    public Recipe editRecipe(@RequestBody Recipe recipe){
        return recipeService.save(recipe);
    }

    @PutMapping("/like/{id}/{userID}")
    public Recipe likeRecipe(@PathVariable Integer id,@PathVariable Integer userID){
        //Get the user who liked the thing
        User current = userService.getUserById(userID);

        //Get the list of recipes that they like
        List<Recipe> liked = current.getLikedRecipes();
        System.out.println(liked);

        //Get the recipe to be liked
        Recipe newLike = recipeService.getRecipeById(id);

        //Check whether the user has already liked this recipe
        boolean likedAlready = liked.contains(newLike);

        //If they have already liked it, remove it from their liked recipes
        if(likedAlready){
            liked.remove(newLike);
            current.setLikedRecipes(liked);
            newLike.getLikers().remove(current);
        }
        //Otherwise, add it to their liked recipes
        else {
            liked.add(newLike);
            current.setLikedRecipes(liked);
            newLike.getLikers().add(current);
        }

        return recipeService.likeRecipe(newLike,likedAlready?-1:1);
    }

    @PutMapping("/view/{id}")
    public Recipe viewRecipe(@PathVariable Integer id){
        return recipeService.viewRecipe(recipeService.getRecipeById(id));
    }

    @GetMapping("/liked/{userId}")
    public List<Recipe> getFavorites(@PathVariable Integer userId){
        return recipeService.getLikedRecipes(userService.getUserById(userId));
    }

    //Get possible ingredients
    //Get units of measure
}

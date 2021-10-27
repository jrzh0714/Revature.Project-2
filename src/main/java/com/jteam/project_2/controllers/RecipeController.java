package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Ingredient;
import org.apache.commons.lang.SerializationUtils;
import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.Step;
import com.jteam.project_2.models.StepIngredient;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    /**
     * Gets a recipe based on its id
     * @param id the unique identifier of a recipe record
     * @return a recipe with the specified id
     */
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    /**
     * Gets recipes that contain a specified string in their titles
     * @param containing the string that is contained in the title
     * @return a list of recipes that match
     */
    @GetMapping("/containing/{containing}")
    public List<Recipe> getRecipesByName(@PathVariable String containing){
        return recipeService.searchRecipesByName(containing);
    }

    /**
     * Gets a list of recipes that start with a specified string
     * @param startingWith the string that the title of each of the returned recipes starts with
     * @return a list of matching recipes
     */
    @GetMapping("/starting/{startingWith}")
    public List<Recipe> getRecipesByStartingString(@PathVariable String startingWith){
        return recipeService.searchRecipesByStartingString(startingWith);
    }

    /**
     * Gets a list of recipes that meet the criteria to be considered "trending"
     * @return a list of recipes
     */
    @GetMapping("/trending")
    public List<Recipe> getTrendingRecipes(){
        return recipeService.searchRecipesByTrending();
    }

    /**
     * Allows the user to submit a recipe to the database
     * @param toSubmit the recipe that the user is trying to put in the database
     * @return the recipe that was submitted
     */
    @PostMapping(path="/submitRecipe",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe submitRecipe(@RequestBody Recipe toSubmit){
        logger.debug("toSubmit = " + toSubmit);
        return recipeService.save(toSubmit);
    }

    /**
     * Gets a list of recipes based on rating
     * @param rating the minimum rating of the recipes to be returned
     * @return a list of recipes above a minimum rating
     */
    @GetMapping("/byRating/{rating}")
    public List<Recipe> getRecipesAboveRating(@PathVariable double rating){
        return recipeService.getRecipesByRating(rating);
    }

    /**
     * Gets a list of recipes based on the specified state
     * @param state the state the recipes of which you desire
     * @return a list of recipes from the given state
     */
    @GetMapping("/byState/{state}")
    public List<Recipe> getRecipesByState(@PathVariable String state){
        return recipeService.getRecipesByState(state);
    }

    /**
     * Gets a list of recipes that contain a specified ingredient
     * @param ingredient the name of the ingredient that you want in the recipe
     * @return a list of recipes that contain an ingredient
     */
    @GetMapping("/byIngredient/{ingredient}")
    public List<Recipe> getRecipesByIngredient(@PathVariable String ingredient){
        return recipeService.searchRecipesByIngredient(ingredient);
    }

    /**
     * Allows the editing of a recipe
     * @param recipe the new form of the recipe
     * @return the recipe that was saved
     */
    @PutMapping("/editRecipe")
    public Recipe editRecipe(@RequestBody Recipe recipe){
        return recipeService.save(recipe);
    }

    /**
     * Does all of the work necessary to "like" a recipe
     * @param id the unique numeric identifier of the recipe record
     * @param userID the unique numeric identifier of the user who is liking the recipe
     * @return the recipe that was liked
     */
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
        logger.debug(newLike.toString());
        return recipeService.likeRecipe(newLike,likedAlready?-1:1);
    }

    /**
     * Officially view a recipe so that it counts in the stats of the recipe
     * @param id the unique numeric identifier of the recipe that is being viewed
     * @return the recipe that was viewed
     */
    @PutMapping("/view/{id}")
    public Recipe viewRecipe(@PathVariable Integer id){
        return recipeService.viewRecipe(recipeService.getRecipeById(id));
    }

    /**
     * Gets the favorite recipes of a specified user
     * @param userId the unique numeric identifier of the user whose favorites are being viewed
     * @return a list of the user's favorite recipes
     */
    @GetMapping("/liked/{userId}")
    public List<Recipe> getFavorites(@PathVariable Integer userId){
        return recipeService.getLikedRecipes(userService.getUserById(userId));
    }
}

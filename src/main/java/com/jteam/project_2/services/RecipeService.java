package com.jteam.project_2.services;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.Step;
import com.jteam.project_2.models.StepIngredient;
import com.jteam.project_2.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;

    //This is for making outgoing HTTP requests to the Bing Images API
    //private final RestTemplate restTemplate;

    //private List<Recipe> allRecipes;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, RestTemplateBuilder restTemplateBuilder) {
        this.recipeRepository = recipeRepository;

        //For outgoing HTTP requests
        //this.restTemplate = restTemplateBuilder.build();

        //allRecipes = findAll();
    }

    public Recipe getRecipeById(int id){
        return recipeRepository.getById(id);
    }

    public List<Recipe> findAll(){
        return recipeRepository.findAll();
    }

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    /**
     * Gets a list of recipes that contain the search string in the title
     * @param name the string that we are searching with
     * @return a list of recipes that match
     */
    public List<Recipe> searchRecipesByName(String name){
        return recipeRepository.getRecipesByNameContainingOrderByLikes(name);
    }

    /**
     * Gets a list of recipes that match a criterion
     * @param start a string to match against
     * @return recipes that start with a search string
     */
    public List<Recipe> searchRecipesByStartingString(String start){
        return recipeRepository.getRecipesByNameStartingWith(start);
    }

    /**
     * Gets a list of recipes based on a default idea of what "trending" represents
     * @return a list of recipes that meet basic criteria of popularity
     */
    public List<Recipe> searchRecipesByTrending(){
        Date targetDate = Date.valueOf(LocalDate.now().minusDays(7));
        return recipeRepository.getRecipesByPublishDateAfterAndLikesGreaterThanOrderByLikes(targetDate,5);
    }

    /**
     * Return a list of recipes that have more than a certain rating, ordered by rating
     * @param rating the minimum acceptable rating
     * @return a list of recipes that have more than the specified rating
     */
    public List<Recipe> getRecipesByRating(double rating){
        return recipeRepository.getRecipesByRatingGreaterThanEqualOrderByRating(rating);
    }

    /**
     * Find a list of recipes published by users in a given state
     * @param state the state that you want to search
     * @return a list of recipes from the given state
     */
    public List<Recipe> getRecipesByState(String state){
        return recipeRepository.getRecipesByUser_Address_StateOrderByRating(state);
    }

    public List<Recipe> searchRecipesByIngredient(String ingredient){
        return recipeRepository.getRecipesByIngredient(ingredient);
    }

    public Recipe likeRecipe(Recipe recipe){
        recipe.setLikes(recipe.getLikes() + 1);
        return recipeRepository.save(recipe);
    }

/*    public Byte[] getImageForRecipe(String recipe) {
        String url = "https://bing-image-search1.p.rapidapi.com/images/search?q=" + recipe;
        Object queryResult = this.restTemplate.getForObject(url,Object.class);
        System.out.println(queryResult);
        //System.out.println(this.restTemplate.getForObject(url, String.class));
        return null;
    }

    private Set<Ingredient> getRecipeIngredients(Recipe recipe){
        Set<Ingredient> myIngredients = new HashSet<>();
        for(Step step: recipe.getRecipeSteps()){
            for(StepIngredient ingredient: step.getStepIngredients()){
                myIngredients.add(ingredient.getIngredient());
            }
        }
        return myIngredients;
    }*/
}

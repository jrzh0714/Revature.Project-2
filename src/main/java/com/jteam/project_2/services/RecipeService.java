package com.jteam.project_2.services;

import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
}

package com.jteam.project_2.services;

import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Recipe> searchRecipesByName(String name){
        return recipeRepository.getRecipesByNameContainingOrderByLikes(name);
    }
}

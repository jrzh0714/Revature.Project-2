package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.User;
import com.jteam.project_2.services.RecipeService;
import com.jteam.project_2.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{containing}")
    public List<Recipe> getRecipesByName(@PathVariable String containing){
        return recipeService.searchRecipesByName(containing);
    }
}

package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Step;
import com.jteam.project_2.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "recipes")
public class RecipeStepController {
    private RecipeService recipeService;

    @Autowired
    public RecipeStepController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(path="/submitRecipeSteps",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Step> submitRecipeSteps(@RequestBody List<Step> toSubmit){
        System.out.println(toSubmit);
        recipeService.getRecipeById(toSubmit.get(0).getRecipe().getId()).setRecipeSteps(toSubmit);
        return recipeService.getRecipeById(toSubmit.get(0).getRecipe().getId()).getRecipeSteps();
    }
}

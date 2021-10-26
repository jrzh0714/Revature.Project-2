package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="ingredients")
public class IngredientController {
    private IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @GetMapping("all")
    public List<Ingredient> possibleIngredients(){
        return ingredientService.findAll();
    }
}

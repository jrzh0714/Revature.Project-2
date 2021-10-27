package com.jteam.project_2.controllers;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.models.units.volume.*;
import com.jteam.project_2.models.units.weight.*;
import com.jteam.project_2.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("units/volume")
    public Map<String,Integer> getVolumeUnits(){
        Map<String,Integer> volumeUnits = new HashMap<>();
        volumeUnits.put("Teaspoon",new Teaspoon().toInt());
        volumeUnits.put("Tablespoon",new Tablespoon().toInt());
        volumeUnits.put("Gallon",new Gallon().toInt());
        volumeUnits.put("Liter",new Liter().toInt());
        volumeUnits.put("Milliliter",new Milliliter().toInt());
        volumeUnits.put("Pint",new Pint().toInt());
        volumeUnits.put("Quart",new Quart().toInt());
        volumeUnits.put("Cup",new Cup().toInt());
        return volumeUnits;
    }

    @GetMapping("units/weight")
    public Map<String,Integer> getWeightUnits(){
        Map<String,Integer> weightUnits = new HashMap<>();
        weightUnits.put("Gram",new Gram().toInt());
        weightUnits.put("Kilogram",new Kilogram().toInt());
        weightUnits.put("Ounce",new Ounce().toInt());
        weightUnits.put("Pound",new Pound().toInt());
        return weightUnits;
    }
}

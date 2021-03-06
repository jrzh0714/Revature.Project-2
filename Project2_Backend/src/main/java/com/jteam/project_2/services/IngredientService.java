package com.jteam.project_2.services;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Retrieves a list containing all of the ingredients in the database
     * @return a list of all of the ingredients in the database
     */
    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }
}

package com.jteam.project_2.repositories;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
    List<Recipe> getRecipesByNameContainingOrderByLikes(String name);
    List<Recipe> getRecipesByNameStartingWith(String start);
    List<Recipe> getRecipesByPublishDateAfterAndLikesGreaterThanOrderByLikes(Date start, int likes);
    List<Recipe> getRecipesByRatingGreaterThanEqualOrderByRating(double rating);
    List<Recipe> getRecipesByUser_Address_StateOrderByRating(String state);
    List<Recipe> getRecipesByRecipeSteps_StepIngredients_Ingredient(Ingredient ingredient);
}

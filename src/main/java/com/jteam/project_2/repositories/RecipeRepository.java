package com.jteam.project_2.repositories;

import com.jteam.project_2.models.Ingredient;
import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select r from Recipe r inner join Step s on r.id = s.recipe.id inner join StepIngredient si on si.stepId = s.id inner join Ingredient i on si.ingredientId = i.id where i.name=?1")
    List<Recipe> getRecipesByIngredient(String ingredient);

    List<Recipe> getRecipesByLikersOrderByRating(User user);
}

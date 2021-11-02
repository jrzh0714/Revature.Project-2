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
    /**
     * Gets an ordered list recipes that contain the given substring in their title
     * @param name the substring we are checking for
     * @return an ordered list of recipes
     */
    List<Recipe> getRecipesByNameContainingOrderByLikes(String name);

    /**
     * Gets a list of recipes that have titles that start with a given string
     * @param start the string that all of the recipes must start with
     * @return a list of recipes with matching titles
     */
    List<Recipe> getRecipesByNameStartingWith(String start);

    /**
     * Gets a list of recipes that have been published since a date that have more than a threshold of likes
     * @param start the last excluded date
     * @param likes the most likes that will be excluded
     * @return a list of recent recipes in order of popularity
     */
    List<Recipe> getRecipesByPublishDateAfterAndLikesGreaterThanOrderByLikes(Date start, int likes);

    /**
     * Gets a list of recipes of a given quality or higher
     * @param rating the minimum acceptable rating
     * @return a list of recipes ordered by rating
     */
    List<Recipe> getRecipesByRatingGreaterThanEqualOrderByRating(double rating);

    /**
     * Gets a list of recipes by users whose addresses are in a given state
     * @param state the state where the chefs must reside
     * @return a list of recipes from a state, ordered by rating
     */
    List<Recipe> getRecipesByUser_Address_StateOrderByRating(String state);

    /**
     * Gets a list of recipes that contain a specific ingredient
     * @param ingredient the ingredient that must be included in the recipe
     * @return a list of ingredient that have a certain ingredient
     */
    @Query(value = "select r from Recipe r inner join Step s on r.id = s.recipe.id inner join StepIngredient si on si.stepId = s.id inner join Ingredient i on si.ingredientId = i.id where i.name=?1")
    List<Recipe> getRecipesByIngredient(String ingredient);

    /**
     * Gets a list of recipes that were liked by the specified user
     * @param user the user whose liked recipes are being retrieved
     * @return a list of the user's favorite recipes, ordered by rating
     */
    List<Recipe> getRecipesByLikersOrderByRating(User user);
}

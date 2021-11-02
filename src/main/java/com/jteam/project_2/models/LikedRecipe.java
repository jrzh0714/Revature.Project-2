package com.jteam.project_2.models;

import javax.persistence.*;

@Entity
@Table(name = "liked_recipes")
public class LikedRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_id")
    private int likedID;

    @Column(name = "user_id")
    private int likerID;

    @Column(name = "recipe_id")
    private int likedRecipeID;

    @Column(name = "times_cooked")
    private int timesCooked;
}

package com.jteam.project_2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "recipes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private long id;

    @Column(name="recipe_name")
    private String name;

    @Column(name="rating")
    private double rating;

    @Column(name="thumbnail")
    private byte[] thumbnail;

    @Column(name="likes")
    private int likes;

    @Column(name="view_count")
    private int viewCount;
}

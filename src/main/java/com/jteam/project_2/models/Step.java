package com.jteam.project_2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "steps")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Step {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="step_id")
    private int id;

    @Column(name="recipe_id")
    private int recipeId;

    @Column(name="step_desc")
    private String stepDescription;

    @Column(name="step_number")
    private int stepNumber;

    @Column(name="image")
    private byte[] image;
}

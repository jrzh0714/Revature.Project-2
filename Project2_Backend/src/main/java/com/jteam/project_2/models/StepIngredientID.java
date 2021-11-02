package com.jteam.project_2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StepIngredientID implements Serializable {
    private int ingredientId;

    private int stepId;

}

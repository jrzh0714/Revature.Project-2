package com.jteam.project_2.models.units.weight;

import com.jteam.project_2.models.StepIngredient;
import lombok.Getter;

@Getter
public class WeightUnit {

    protected String unitName;
    protected double factor;

    public void convertUnit(StepIngredient stepIngredient) {

    }
}

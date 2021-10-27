package com.jteam.project_2.models.units.weight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jteam.project_2.models.StepIngredient;
import lombok.Getter;

@Getter
@JsonDeserialize(as=Weight.class)
public abstract class WeightUnit {

    protected String unitName;
    protected double factor;

    /**
     * Converts a stepIngredient from one unit of weight to another
     * @param stepIngredient The stepIngredient to be converted.
     */
    public void convertUnit(StepIngredient stepIngredient) {
        WeightUnit oldWeightUnit = stepIngredient.getWeightUnit();
        double newWeight = stepIngredient.getWeight() * (oldWeightUnit.getFactor() / factor);
        stepIngredient.setWeight(newWeight);
        stepIngredient.setWeightUnit(this);
    }

    public abstract Integer toInt();
}

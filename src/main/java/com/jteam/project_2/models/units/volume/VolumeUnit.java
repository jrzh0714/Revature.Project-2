package com.jteam.project_2.models.units.volume;

import com.jteam.project_2.models.StepIngredient;
import lombok.Getter;

@Getter
public abstract class VolumeUnit {

    protected String unitName;
    protected double factor;

    public void convertUnit(StepIngredient stepIngredient) {

    }

}

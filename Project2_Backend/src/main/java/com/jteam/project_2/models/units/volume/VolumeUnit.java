package com.jteam.project_2.models.units.volume;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jteam.project_2.models.StepIngredient;
import com.jteam.project_2.models.units.weight.WeightUnit;
import lombok.Getter;

@Getter
@JsonDeserialize(as= Volume.class)
public abstract class VolumeUnit {

    protected String unitName;
    protected double factor;

    /**
     * Converts a stepIngredient from one unit of volume to another.
     * @param stepIngredient The stepIngredient to be converted.
     */
    public void convertUnit(StepIngredient stepIngredient) {
        VolumeUnit oldVolumeUnit = stepIngredient.getVolumeUnit();
        double newVolume = stepIngredient.getVolume() * (oldVolumeUnit.getFactor() / factor);
        stepIngredient.setVolume(newVolume);
        stepIngredient.setVolumeUnit(this);
    }

    public abstract Integer toInt();

}

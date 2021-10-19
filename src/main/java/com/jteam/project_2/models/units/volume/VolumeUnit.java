package com.jteam.project_2.models.units.volume;

import com.jteam.project_2.models.StepIngredient;
import lombok.Getter;

@Getter
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

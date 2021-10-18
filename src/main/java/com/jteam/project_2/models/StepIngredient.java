package com.jteam.project_2.models;


import com.jteam.project_2.models.units.volume.VolumeUnit;
import com.jteam.project_2.models.units.weight.WeightUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepIngredient {       //TODO - This is just the skeleton for testing, needs finishing.

    private Ingredient ingredient;
    private Double weight;
    private Double volume;
    private VolumeUnit volumeUnit;
    private WeightUnit weightUnit;
}

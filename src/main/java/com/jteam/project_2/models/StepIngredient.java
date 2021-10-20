package com.jteam.project_2.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jteam.project_2.converters.VolumeUnitConverter;
import com.jteam.project_2.converters.WeightUnitConverter;
import com.jteam.project_2.models.units.volume.VolumeUnit;
import com.jteam.project_2.models.units.weight.WeightUnit;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "step_ingredients")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StepIngredientID.class)

public class StepIngredient implements Serializable {       //TODO - Needs double checking for database connection

    @Id
    @Column(name="ingredient_id")

    private int ingredientId;

    @JsonBackReference
    @JoinColumn(name = "ingredient_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @MapsId
    private Ingredient ingredient;

    @Id
    @Column(name="step_id")
    private int stepId;

    @JsonBackReference
    @JoinColumn(name = "step_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false,fetch = FetchType.LAZY)
    @MapsId
    private Step step;

    @Column(name = "amount_weight")
    private Double weight;

    @Column(name = "amount_volume")
    private Double volume;

    @Convert(converter = VolumeUnitConverter.class)
    @Column(name = "amount_volume_unit")
    private VolumeUnit volumeUnit;

    @Convert(converter = WeightUnitConverter.class)
    @Column(name = "amount_weight_unit")
    private WeightUnit weightUnit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepIngredient that = (StepIngredient) o;
        return ingredientId == that.ingredientId && stepId == that.stepId && Objects.equals(ingredient, that.ingredient) && Objects.equals(step, that.step) && Objects.equals(weight, that.weight) && Objects.equals(volume, that.volume) && Objects.equals(volumeUnit, that.volumeUnit) && Objects.equals(weightUnit, that.weightUnit);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "StepIngredient{" +
                "ingredientId=" + ingredientId +
                ", stepId=" + stepId +
                ", weight=" + weight +
                ", volume=" + volume +
                ", volumeUnit=" + volumeUnit +
                ", weightUnit=" + weightUnit +
                '}';
    }
}
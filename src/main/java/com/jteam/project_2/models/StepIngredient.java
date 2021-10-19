package com.jteam.project_2.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jteam.project_2.converters.VolumeUnitConverter;
import com.jteam.project_2.converters.WeightUnitConverter;
import com.jteam.project_2.models.units.volume.VolumeUnit;
import com.jteam.project_2.models.units.weight.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "step_ingredients")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepIngredient {       //TODO - Needs double checking for database connection

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ingredient ingredient;

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
}

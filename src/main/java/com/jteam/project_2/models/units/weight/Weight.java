package com.jteam.project_2.models.units.weight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Weight extends WeightUnit implements Serializable {
    String weightUnit;

    public Weight(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    @Override
    public Integer toInt() {
        return null;
    }
}

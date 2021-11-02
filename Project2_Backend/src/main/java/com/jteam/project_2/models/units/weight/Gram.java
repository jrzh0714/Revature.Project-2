package com.jteam.project_2.models.units.weight;

public class Gram extends WeightUnit{
    public Gram() {
        unitName = "gram";
        factor = 0.035274;
    }

    @Override
    public Integer toInt() {
        return 3;
    }
}

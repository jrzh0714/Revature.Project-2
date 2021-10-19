package com.jteam.project_2.models.units.weight;

public class Kilogram extends WeightUnit{
    public Kilogram() {
        unitName = "kilogram";
        factor = 35.274;
    }

    @Override
    public Integer toInt() {
        return 4;
    }
}

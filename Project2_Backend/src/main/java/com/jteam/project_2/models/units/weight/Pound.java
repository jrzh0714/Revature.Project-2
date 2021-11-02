package com.jteam.project_2.models.units.weight;

public class Pound extends WeightUnit{
    public Pound() {
        unitName = "pound";
        factor = 16;
    }

    @Override
    public Integer toInt() {
        return 2;
    }
}

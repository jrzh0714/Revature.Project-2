package com.jteam.project_2.models.units.weight;

public class Ounce extends WeightUnit{
    public Ounce(){
        unitName = "ounce";
        factor = 1;
    }

    @Override
    public Integer toInt() {
        return 1;
    }
}

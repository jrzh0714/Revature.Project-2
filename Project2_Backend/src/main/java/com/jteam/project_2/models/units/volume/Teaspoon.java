package com.jteam.project_2.models.units.volume;

public class Teaspoon extends VolumeUnit{

    public Teaspoon() {
        unitName = "teaspoon";
        factor = 1;
    }

    @Override
    public Integer toInt() {
        return 1;
    }
}

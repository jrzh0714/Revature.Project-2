package com.jteam.project_2.models.units.volume;

public class Gallon extends VolumeUnit{
    public Gallon() {
        unitName = "gallon";
        factor = 768;
    }

    @Override
    public Integer toInt() {
        return 6;
    }
}

package com.jteam.project_2.models.units.volume;

public class Pint extends VolumeUnit{
    public Pint() {
        unitName = "pint";
        factor = 96;
    }

    @Override
    public Integer toInt() {
        return 4;
    }
}

package com.jteam.project_2.models.units.volume;

public class Liter extends VolumeUnit{

    public Liter() {
        unitName = "liter";
        factor = 202.884;
    }

    @Override
    public Integer toInt() {
        return 8;
    }
}

package com.jteam.project_2.models.units.volume;

public class Milliliter extends VolumeUnit{

    public Milliliter() {
        unitName = "milliliter";
        factor = 0.202884;
    }

    @Override
    public Integer toInt() {
        return 7;
    }
}

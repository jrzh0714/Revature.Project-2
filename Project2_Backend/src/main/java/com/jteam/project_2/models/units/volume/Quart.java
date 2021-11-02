package com.jteam.project_2.models.units.volume;

public class Quart extends VolumeUnit{
    public Quart() {
        unitName = "quart";
        factor = 192;
    }

    @Override
    public Integer toInt() {
        return 5;
    }
}

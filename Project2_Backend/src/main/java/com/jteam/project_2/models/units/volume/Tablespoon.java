package com.jteam.project_2.models.units.volume;

public class Tablespoon extends VolumeUnit{

    public Tablespoon() {
        unitName = "tablespoon";
        factor = 3;
    }

    @Override
    public Integer toInt() {
        return 2;
    }
}

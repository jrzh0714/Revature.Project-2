package com.jteam.project_2.models.units.volume;

public class Cup extends VolumeUnit{
    public Cup() {
        unitName = "cup";
        factor = 48;
    }

    @Override
    public Integer toInt() {
        return 3;
    }
}

package com.jteam.project_2.models.units.volume;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Volume extends VolumeUnit implements Serializable {
    String volumeUnit;

    public Volume(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    @Override
    public Integer toInt() {
        return null;
    }
}

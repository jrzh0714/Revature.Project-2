package com.jteam.project_2.converters;

import com.jteam.project_2.models.units.volume.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class VolumeUnitConverter implements AttributeConverter<VolumeUnit, Integer> {
    @Override
    public Integer convertToDatabaseColumn(VolumeUnit volumeUnit) {
        if (volumeUnit == null)
            return null;
        return volumeUnit.toInt();
    }

    @Override
    public VolumeUnit convertToEntityAttribute(Integer newVolumeUnitIndex) {
        VolumeUnit newVolumeUnit;
        if (newVolumeUnitIndex == null)
            return null;
        int i = newVolumeUnitIndex.intValue();
        switch(i) {
            case 1:
                newVolumeUnit = new Teaspoon();
                break;
            case 2:
                newVolumeUnit = new Tablespoon();
                break;
            case 3:
                newVolumeUnit = new Cup();
                break;
            case 4:
                newVolumeUnit = new Pint();
                break;
            case 5:
                newVolumeUnit = new Quart();
                break;
            case 6:
                newVolumeUnit = new Gallon();
                break;
            case 7:
                newVolumeUnit = new Milliliter();
                break;
            case 8:
                newVolumeUnit = new Liter();
                break;
            default:
                throw new IndexOutOfBoundsException("newVolumeUnitIndex out of bounds! newVolumeUnitIndex = " + i);
        }
        return newVolumeUnit;
    }
}

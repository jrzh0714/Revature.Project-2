package com.jteam.project_2.converters;

import com.jteam.project_2.models.units.weight.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WeightUnitConverter implements AttributeConverter<WeightUnit, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WeightUnit weightUnit) {
        if (weightUnit == null)
            return null;
        return weightUnit.toInt();
    }

    @Override
    public WeightUnit convertToEntityAttribute(Integer newWeightUnitIndex) {
        WeightUnit newWeightUnit;
        if (newWeightUnitIndex == null)
            return null;
        int i = newWeightUnitIndex.intValue();
        switch(i) {
            case 1:
                newWeightUnit = new Ounce();
                break;
            case 2:
                newWeightUnit = new Pound();
                break;
            case 3:
                newWeightUnit = new Gram();
                break;
            case 4:
                newWeightUnit = new Kilogram();
                break;
            default:
                throw new IndexOutOfBoundsException("newVolumeUnitIndex out of bounds! newVolumeUnitIndex = " + i);
        }
        return newWeightUnit;
    }
}

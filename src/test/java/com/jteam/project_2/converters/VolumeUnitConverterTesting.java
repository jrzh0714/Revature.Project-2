package com.jteam.project_2.converters;

import com.jteam.project_2.models.units.volume.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VolumeUnitConverterTesting {

    private VolumeUnitConverter testVolumeUnitConverter;

    @BeforeEach
    public void init() {
        testVolumeUnitConverter = new VolumeUnitConverter();
    }

    @Test
    public void convertToDatabaseColumnTest() {
        VolumeUnit mockVolumeUnit = mock(VolumeUnit.class);
        when(mockVolumeUnit.toInt()).thenReturn(2);

        Integer testInteger = testVolumeUnitConverter.convertToDatabaseColumn(mockVolumeUnit);
        assertEquals(Integer.valueOf(2), testInteger, "convertToDatabaseColumn returned wrong integer!");
    }

    @Test
    public void convertToTeaspoonTest() {
        Integer testInteger = new Integer(1);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Teaspoon, "convertToEntityAttribute did not return a Teaspoon!");
    }

    @Test
    public void convertToTablespoonTest() {
        Integer testInteger = new Integer(2);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Tablespoon, "convertToEntityAttribute did not return a Tablespoon!");
    }

    @Test
    public void convertToCupTest() {
        Integer testInteger = new Integer(3);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Cup, "convertToEntityAttribute did not return a Cup!");
    }

    @Test
    public void convertToPintTest() {
        Integer testInteger = new Integer(4);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Pint, "convertToEntityAttribute did not return a Pint!");
    }

    @Test
    public void convertToQuartTest() {
        Integer testInteger = new Integer(5);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Quart, "convertToEntityAttribute did not return a Quart!");
    }

    @Test
    public void convertToGallonTest() {
        Integer testInteger = new Integer(6);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Gallon, "convertToEntityAttribute did not return a Gallon!");
    }

    @Test
    public void convertToMilliliterTest() {
        Integer testInteger = new Integer(7);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Milliliter, "convertToEntityAttribute did not return a Milliliter!");
    }

    @Test
    public void convertToLiterTest() {
        Integer testInteger = new Integer(8);
        VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testVolumeUnit instanceof Liter, "convertToEntityAttribute did not return a Liter!");
    }

    @Test
    public void convertToErrorTest() {
        Integer testInteger = new Integer(10000);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            VolumeUnit testVolumeUnit = testVolumeUnitConverter.convertToEntityAttribute(testInteger);
        });
    }
}

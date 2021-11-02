package com.jteam.project_2.converters;

import com.jteam.project_2.models.units.weight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeightUnitConverterTesting {

    private WeightUnitConverter testWeightUnitConverter;

    @BeforeEach
    public void init() {
        testWeightUnitConverter = new WeightUnitConverter();
    }

    @Test
    public void convertToDatabaseColumnTest() {
        WeightUnit mockWeightUnit = mock(WeightUnit.class);
        when(mockWeightUnit.toInt()).thenReturn(2);

        Integer testInteger = testWeightUnitConverter.convertToDatabaseColumn(mockWeightUnit);
        assertEquals(Integer.valueOf(2), testInteger, "convertToDatabaseColumn returned wrong integer!");
    }

    @Test
    public void convertToOunceTest() {
        Integer testInteger = new Integer(1);
        WeightUnit testWeightUnit = testWeightUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testWeightUnit instanceof Ounce, "convertToEntityAttribute did not return a Ounce!");
    }

    @Test
    public void convertToPoundTest() {
        Integer testInteger = new Integer(2);
        WeightUnit testWeightUnit = testWeightUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testWeightUnit instanceof Pound, "convertToEntityAttribute did not return a Pound!");
    }

    @Test
    public void convertToGramTest() {
        Integer testInteger = new Integer(3);
        WeightUnit testWeightUnit = testWeightUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testWeightUnit instanceof Gram, "convertToEntityAttribute did not return a Gram!");
    }

    @Test
    public void convertToKilogramTest() {
        Integer testInteger = new Integer(4);
        WeightUnit testWeightUnit = testWeightUnitConverter.convertToEntityAttribute(testInteger);

        assertTrue(testWeightUnit instanceof Kilogram, "convertToEntityAttribute did not return a Kilogram!");
    }

    @Test
    public void convertToErrorTest() {
        Integer testInteger = new Integer(10000);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            WeightUnit testWeightUnit = testWeightUnitConverter.convertToEntityAttribute(testInteger);
        });
    }
}

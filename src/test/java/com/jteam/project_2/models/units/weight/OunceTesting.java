package com.jteam.project_2.models.units.weight;

import com.jteam.project_2.models.StepIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OunceTesting {
    private Ounce testOunce;

    @Captor
    ArgumentCaptor<Double> weightCaptor;

    @BeforeEach
    public void init() {
        testOunce = new Ounce();
    }

    @Test
    public void ConvertTest() {
        StepIngredient mockStepIngredient = mock(StepIngredient.class);
        WeightUnit mockWeightUnit = mock(WeightUnit.class);

        when(mockStepIngredient.getWeight()).thenReturn(10.0);
        when(mockStepIngredient.getWeightUnit()).thenReturn(mockWeightUnit);

        when(mockWeightUnit.getFactor()).thenReturn(0.1);

        testOunce.convertUnit(mockStepIngredient);

        verify(mockStepIngredient).setWeight(weightCaptor.capture());
        Double newWeight = weightCaptor.getValue();

        assertEquals(1, newWeight.doubleValue(),0.001, "Weight wrong after unit conversion!");
        verify(mockStepIngredient).setWeightUnit(testOunce);
    }
}

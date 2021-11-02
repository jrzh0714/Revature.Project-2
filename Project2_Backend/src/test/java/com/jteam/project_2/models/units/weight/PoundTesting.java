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
public class PoundTesting {
    private Pound testPound;

    @Captor
    ArgumentCaptor<Double> weightCaptor;

    @BeforeEach
    public void init() {
        testPound = new Pound();
    }

    @Test
    public void ConvertTest() {
        StepIngredient mockStepIngredient = mock(StepIngredient.class);
        WeightUnit mockWeightUnit = mock(WeightUnit.class);

        when(mockStepIngredient.getWeight()).thenReturn(1.0);
        when(mockStepIngredient.getWeightUnit()).thenReturn(mockWeightUnit);

        when(mockWeightUnit.getFactor()).thenReturn(32.0);

        testPound.convertUnit(mockStepIngredient);

        verify(mockStepIngredient).setWeight(weightCaptor.capture());
        Double newWeight = weightCaptor.getValue();

        assertEquals(2, newWeight.doubleValue(),0.001, "Weight wrong after unit conversion!");
        verify(mockStepIngredient).setWeightUnit(testPound);
    }
}

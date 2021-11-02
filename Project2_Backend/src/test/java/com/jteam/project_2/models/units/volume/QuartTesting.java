package com.jteam.project_2.models.units.volume;

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
public class QuartTesting {
    private Quart testQuart;

    @Captor
    ArgumentCaptor<Double> volumeCaptor;

    @BeforeEach
    public void init() {
        testQuart = new Quart();
    }

    @Test
    public void ConvertTest() {
        StepIngredient mockStepIngredient = mock(StepIngredient.class);
        VolumeUnit mockVolumeUnit = mock(VolumeUnit.class);

        when(mockStepIngredient.getVolume()).thenReturn(1.0);
        when(mockStepIngredient.getVolumeUnit()).thenReturn(mockVolumeUnit);

        when(mockVolumeUnit.getFactor()).thenReturn(768.0);

        testQuart.convertUnit(mockStepIngredient);

        verify(mockStepIngredient).setVolume(volumeCaptor.capture());
        Double newVolume = volumeCaptor.getValue();

        assertEquals(4.0, newVolume.doubleValue(),0.001, "Volume wrong after unit conversion!");
        verify(mockStepIngredient).setVolumeUnit(testQuart);
    }

    @Test
    public void toIntTest() {
        assertEquals(Integer.valueOf(5), testQuart.toInt());
    }
}

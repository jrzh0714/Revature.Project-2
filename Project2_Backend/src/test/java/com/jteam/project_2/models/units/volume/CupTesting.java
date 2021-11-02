package com.jteam.project_2.models.units.volume;

import com.jteam.project_2.models.StepIngredient;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CupTesting {

    private Cup testCup;

    @Captor
    ArgumentCaptor<Double> volumeCaptor;

    @BeforeEach
    public void init() {
        testCup = new Cup();
    }

    @Test
    public void ConvertTest() {
        StepIngredient mockStepIngredient = mock(StepIngredient.class);
        VolumeUnit mockVolumeUnit = mock(VolumeUnit.class);

        when(mockStepIngredient.getVolume()).thenReturn(16.0);
        when(mockStepIngredient.getVolumeUnit()).thenReturn(mockVolumeUnit);

        when(mockVolumeUnit.getFactor()).thenReturn(3.0);

        testCup.convertUnit(mockStepIngredient);

        verify(mockStepIngredient).setVolume(volumeCaptor.capture());
        Double newVolume = volumeCaptor.getValue();

        assertEquals(1.0, newVolume.doubleValue(),0.001, "Volume wrong after unit conversion!");
        verify(mockStepIngredient).setVolumeUnit(testCup);
    }

    @Test
    public void toIntTest() {
        assertEquals(Integer.valueOf(3), testCup.toInt());
    }
}

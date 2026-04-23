package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FunctionTest {

    @Mock
    private Function mockSin;

    private final SinFunction realSin = new SinFunction();
    private final double eps = 1e-8;

    @Test
    void sinShouldMatchReferenceValues() {
        double[] testPoints = {0.0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2,
                2*Math.PI/3, 3*Math.PI/4, 5*Math.PI/6, Math.PI};

        for (double x : testPoints) {
            assertEquals(Math.sin(x), realSin.calculate(x, eps), 1e-6, "sin(" + x + ")");
        }
    }

    @Test
    void cosShouldUseInjectedSinStub() {
        when(mockSin.calculate(eq(0.0), anyDouble())).thenReturn(0.0);
        when(mockSin.calculate(eq(Math.PI / 2), anyDouble())).thenReturn(1.0);
        when(mockSin.calculate(eq(Math.PI), anyDouble())).thenReturn(0.0);
        when(mockSin.calculate(eq(-Math.PI / 2), anyDouble())).thenReturn(-1.0);  // 添加这行！

        CosFunction cosWithMockSin = new CosFunction(mockSin);

        assertEquals(1.0, cosWithMockSin.calculate(0.0, eps), 1e-6);
        assertEquals(0.0, cosWithMockSin.calculate(Math.PI / 2, eps), 1e-6);
        assertEquals(-1.0, cosWithMockSin.calculate(Math.PI, eps), 1e-6);
    }

    @Test
    void cosShouldSatisfyIdentityUsingMathAndImplementation() {
        SinFunction realSinImpl = new SinFunction();
        CosFunction cosImpl = new CosFunction(realSinImpl);

        double[] testPoints = {0.0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2,
                2*Math.PI/3, 3*Math.PI/4, 5*Math.PI/6, Math.PI};

        for (double x : testPoints) {
            double sin = realSinImpl.calculate(x, eps);
            double cos = cosImpl.calculate(x, eps);

            assertEquals(1.0, sin * sin + cos * cos, 1e-6, "Identity failed for x = " + x);
        }
    }

    @Test
    void sinInvalidEpsilonShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> realSin.calculate(1.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> realSin.calculate(1.0, -1e-6));
    }
}
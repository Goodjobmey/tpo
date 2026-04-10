package com.math;

import com.stub.SinStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionTest {

    private final SinFunction sin = new SinFunction();
    private final double eps = 1e-8;

    @Test
    void sinShouldMatchReferenceValues() {
        SinStub sinStub = new SinStub();

        for (double[] point : sinStub.getTable()) {
            double x = point[0];
            assertEquals(Math.sin(x), sin.calculate(x, eps), 1e-6, "sin(" + x + ")");
        }
    }

    @Test
    void cosShouldUseInjectedSinStub() {
        SinStub sinStub = new SinStub();
        CosFunction cosWithStub = new CosFunction(sinStub);

        for (double[] point : sinStub.getTable()) {
            double x = point[0];
            assertEquals(Math.cos(x), cosWithStub.calculate(x, eps), 1e-6, "cos(" + x + ")");
        }
    }

    @Test
    void sinInvalidEpsilonShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> sin.calculate(1.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> sin.calculate(1.0, -1e-6));
    }
}

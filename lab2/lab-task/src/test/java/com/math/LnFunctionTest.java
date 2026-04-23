package com.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnFunctionTest {

    private final LnFunction realLn = new LnFunction();
    private final double eps = 1e-8;

    @Test
    void valuesLessThanOne() {
        double[] inputs = {0.1, 0.25, 0.5, 0.9};
        for (double x : inputs) {
            assertEquals(Math.log(x), realLn.calculate(x, eps), 1e-4, "ln(" + x + ")");
        }
    }

    @Test
    void valueOne() {
        assertEquals(0, realLn.calculate(1, eps), 1e-4);
    }

    @Test
    void valuesGreaterThanOne() {
        double[] inputs = {2, 3, Math.E, 10, 100};
        for (double x : inputs) {
            assertEquals(Math.log(x), realLn.calculate(x, eps), 1e-4, "ln(" + x + ")");
        }
    }

    @Test
    void verySmallAndVeryLargeValues() {
        double[] inputs = {1e-6, 1e-5, 1e5, 1e6};
        for (double x : inputs) {
            assertEquals(Math.log(x), realLn.calculate(x, eps), 1e-2, "ln(" + x + ")");
        }
    }

    @Test
    void invalidXShouldThrow() {
        double[] invalidInputs = {0, -0.1, -5};
        for (double x : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> realLn.calculate(x, eps));
        }
    }

    @Test
    void invalidEpsilonShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> realLn.calculate(2, 0));
        assertThrows(IllegalArgumentException.class, () -> realLn.calculate(2, -1e-6));
    }
}
package com.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LnFunctionTest {

    private final LnFunction ln = new LnFunction();
    private final double eps = 1e-8;
    private final double ep = 1e-4;

    @Test
    public void testValuesLessThanOne() {
        double[] inputs = {0.1, 0.25, 0.5, 0.9};
        for (double x : inputs) {
            assertEquals(Math.log(x), ln.calculate(x, eps), ep,
                    "ln(" + x + ") должен совпадать с Math.log");
        }
    }

    @Test
    public void testValueOne() {
        assertEquals(0, ln.calculate(1, eps), ep, "ln(1) должен быть 0");
    }


    @Test
    public void testValuesGreaterThanOne() {
        double[] inputs = {2, 3, Math.E, 10, 100};
        for (double x : inputs) {
            assertEquals(Math.log(x), ln.calculate(x, eps), ep,
                    "ln(" + x + ") должен совпадать с Math.log");
        }
    }

    @Test
    public void testVerySmallAndVeryLargeValues() {
        double[] inputs = {1e-6, 1e-5, 1e5, 1e6};
        for (double x : inputs) {
            assertEquals(Math.log(x), ln.calculate(x, eps), 1e-2,
                    "ln(" + x + ") должен совпадать с Math.log");
        }
    }

    @Test
    public void testNegativeAndZeroValues() {
        double[] invalidInputs = {0, -0.1, -5};
        for (double x : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> ln.calculate(x, eps),
                    "ln(" + x + ") должна выбросить IllegalArgumentException");
        }
    }
}
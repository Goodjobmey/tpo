package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogAFunctionTest {

    private final LnFunction ln = new LnFunction();

    @Test
    void logAShouldMatchMathLog() {
        double[] bases = {2, 3, 5, 10};
        double[] inputs = {0.25, 0.5, 1.0, 2.0, 3.0, 10.0, 1e-4, 1e4};
        double eps = 1e-6;

        for (double base : bases) {
            Function logA = new LogAFunction(base, ln);
            for (double x : inputs) {
                double expected = Math.log(x) / Math.log(base);
                assertEquals(expected, logA.calculate(x, eps), 1e-2, "log_" + base + "(" + x + ")");
            }
        }
    }

    @Test
    void logAAtOneShouldBeZero() {
        Function log2 = new LogAFunction(2, ln);
        assertEquals(0.0, log2.calculate(1.0, 1e-6), 1e-6);
    }

    @Test
    void logAInvalidXShouldThrow() {
        Function log2 = new LogAFunction(2, ln);
        double[] invalidInputs = {0.0, -1.0, -10.0};

        for (double x : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> log2.calculate(x, 1e-6));
        }
    }

    @Test
    void invalidBaseShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new LogAFunction(1.0, ln));
        assertThrows(IllegalArgumentException.class, () -> new LogAFunction(0.0, ln));
        assertThrows(IllegalArgumentException.class, () -> new LogAFunction(-2.0, ln));
    }
}

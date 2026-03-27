package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogAFunctionTest {

    private final LnFunction ln = new LnFunction();

    @Test
    void testLogAAgainstMathLog() {
        double[] bases = {2, 3, 5, 10};
        double[] inputs = {0.25, 0.5, 1.0, 2.0, 3.0, 10.0, 1e-4, 1e4};

        double eps = 1e-6;
        double ep = 1e-2;

        for (double base : bases) {
            Function logA = new LogAFunction(base, ln);

            for (double x : inputs) {
                double expected = Math.log(x) / Math.log(base);
                double actual = logA.calculate(x, eps);

                assertEquals(expected, actual, ep,
                        "Ошибка для log_" + base + "(" + x + ")");
            }
        }
    }

    @Test
    void testLogAAtOne() {
        Function log2 = new LogAFunction(2, ln);
        double epsilon = 1e-6;

        assertEquals(0.0, log2.calculate(1.0, epsilon), 1e-5);
    }

    @Test
    void testInvalidX() {
        Function log2 = new LogAFunction(2, ln);
        double epsilon = 1e-6;

        double[] invalids = {0.0, -1.0, -10.0};

        for (double x : invalids) {
            assertThrows(IllegalArgumentException.class,
                    () -> log2.calculate(x, epsilon),
                    "Должно падать для x=" + x);
        }
    }
}
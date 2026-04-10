package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompleteFunctionTest {

    private final LnFunction ln = new LnFunction();
    private final SinFunction sin = new SinFunction();
    private final CosFunction cos = new CosFunction(sin);
    private final Function complete = new CompleteFunction(
            cos,
            sin,
            ln,
            new LogAFunction(2, ln),
            new LogAFunction(3, ln),
            new LogAFunction(5, ln),
            new LogAFunction(10, ln)
    );

    private final double epsilon = 1e-8;
    private final double delta = 1e-2;

    @Test
    void negativeAndZeroValuesShouldUseTrigBranch() {
        double[] inputs = {-10, -1, -0.5, 0};

        for (double x : inputs) {
            double expected = cos.calculate(x, epsilon) - sin.calculate(x, epsilon);
            assertEquals(expected, complete.calculate(x, epsilon), delta, "x=" + x);
        }
    }

    @Test
    void positiveValuesShouldMatchReferenceFormula() {
        double[] inputs = {0.5, 2.0, 3.5, 10.0, 1e-4, 1e4};

        for (double x : inputs) {
            double log2x = Math.log(x) / Math.log(2);
            double log3x = Math.log(x) / Math.log(3);
            double log5x = Math.log(x) / Math.log(5);
            double log10x = Math.log(x) / Math.log(10);

            double log2xSquared = log2x * log2x;
            double a = ((log5x + log10x) / log2x) * log3x;
            double b = a / (Math.log(x) / log3x);
            double c = log2xSquared + log2xSquared;
            double expected = b - c;

            assertEquals(expected, complete.calculate(x, epsilon), delta, "x=" + x);
        }
    }

    @Test
    void xEqualsOneShouldThrow() {
        assertThrows(ArithmeticException.class, () -> complete.calculate(1.0, epsilon));
    }
}

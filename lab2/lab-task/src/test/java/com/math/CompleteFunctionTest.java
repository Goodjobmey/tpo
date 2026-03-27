package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompleteFunctionTest {

    private final LnFunction ln = new LnFunction();
    private final SinFunction sin = new SinFunction();
    private final CosFunction cos = new CosFunction(sin);
    private final Function complete = new CompleteFunction(cos, sin, ln,
            new LogAFunction(2, ln),
            new LogAFunction(3, ln),
            new LogAFunction(5, ln),
            new LogAFunction(10, ln));

    private final double epsilon = 1e-8;
    private final double delta = 1e-2;

    // Тест для отрицательных значений (x <= 0)
    @Test
    void testNegativeAndZeroValues() {
        double[] inputs = {-10, -1, -0.5, 0};

        for (double x : inputs) {
            double expected = cos.calculate(x, epsilon) - sin.calculate(x, epsilon);
            double actual = complete.calculate(x, epsilon);
            assertEquals(expected, actual, delta, "Ошибка для x=" + x);
        }
    }

    // Тест для положительных значений (x > 0, кроме 1)
    @Test
    void testPositiveValues() {
        double[] inputs = {0.5, 2.0, 3.5, 10.0, 1e-4, 1e4};

        for (double x : inputs) {
            if (Math.abs(x - 1.0) < 1e-12) continue; // пропускаем 1
            double log2x = Math.log(x) / Math.log(2);
            double log3x = Math.log(x) / Math.log(3);
            double log5x = Math.log(x) / Math.log(5);
            double log10x = Math.log(x) / Math.log(10);

            double log2xSquared = log2x * log2x;
            double A = ((log5x + log10x) / log2x) * log3x;
            double B = A / (Math.log(x) / log3x);
            double C = log2xSquared + log2xSquared;
            double expected = B - C;

            double actual = complete.calculate(x, epsilon);
            assertEquals(expected, actual, delta, "Ошибка для x=" + x);
        }
    }

    // Проверка на x = 1 (деление на 0)
    @Test
    void testXEqualsOne() {
        assertThrows(ArithmeticException.class,
                () -> complete.calculate(1.0, epsilon),
                "Должно выбрасывать исключение для x=1");
    }
}
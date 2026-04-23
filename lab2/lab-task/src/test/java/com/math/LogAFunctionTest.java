package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LogAFunctionTest {

    @Mock
    private Function mockLn;

    private final LnFunction realLn = new LnFunction();

    @Test
    void logAShouldMatchMathLog() {
        double[] bases = {2, 3, 5, 10};
        double[] inputs = {0.25, 0.5, 1.0, 2.0, 3.0, 10.0, 1e-4, 1e4};
        double eps = 1e-6;

        for (double base : bases) {
            LogAFunction logA = new LogAFunction(base, realLn);
            for (double x : inputs) {
                double expected = Math.log(x) / Math.log(base);
                assertEquals(expected, logA.calculate(x, eps), 1e-2, "log_" + base + "(" + x + ")");
            }
        }
    }

    @Test
    void logAAtOneShouldBeZero() {
        LogAFunction log2 = new LogAFunction(2, realLn);
        assertEquals(0.0, log2.calculate(1.0, 1e-6), 1e-6);
    }

    @Test
    void logAInvalidXShouldThrow() {
        LogAFunction log2 = new LogAFunction(2, realLn);
        double[] invalidInputs = {0.0, -1.0, -10.0};

        for (double x : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> log2.calculate(x, 1e-6));
        }
    }

    @Test
    void logAShouldMatchLnBasedIdentity() {
        double[] bases = {2, 3, 5, 10};
        double[] inputs = {0.25, 0.5, 1.0, 2.0, 3.0, 10.0, 1e-4, 1e4};
        double eps = 1e-8;

        for (double base : bases) {
            LogAFunction logA = new LogAFunction(base, realLn);

            for (double x : inputs) {
                double lnX = realLn.calculate(x, eps);
                double lnBase = realLn.calculate(base, eps);

                double expected = lnX / lnBase;
                double actual = logA.calculate(x, eps);

                assertEquals(expected, actual, 1e-4,
                        "ln-based identity failed for log_" + base + "(" + x + ")");
            }
        }
    }

    @Test
    void logAWithMockLnShouldWork() {
        double ln4 = Math.log(4);
        double ln2 = Math.log(2);

        when(mockLn.calculate(eq(4.0), anyDouble())).thenReturn(ln4);
        when(mockLn.calculate(eq(2.0), anyDouble())).thenReturn(ln2);

        LogAFunction log2WithMockLn = new LogAFunction(2.0, mockLn);

        double expected = ln4 / ln2;
        assertEquals(expected, log2WithMockLn.calculate(4.0, 1e-6), 1e-9);

        verify(mockLn, times(1)).calculate(eq(4.0), anyDouble());
        verify(mockLn, times(1)).calculate(eq(2.0), anyDouble());
    }

    @Test
    void invalidBaseShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new LogAFunction(1.0, realLn));
        assertThrows(IllegalArgumentException.class, () -> new LogAFunction(0.0, realLn));
        assertThrows(IllegalArgumentException.class, () -> new LogAFunction(-2.0, realLn));
    }
}
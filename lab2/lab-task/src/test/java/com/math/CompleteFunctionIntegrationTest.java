package com.math;

import com.back.Function;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompleteFunctionIntegrationTest {

    private static final double EPSILON = 1e-8;
    private static final double DELTA = 1e-4;

    @Test
    void integrationStepAllStubs() {
        Function mockCos = mock(Function.class);
        Function mockSin = mock(Function.class);
        Function mockLn = mock(Function.class);
        Function mockLog2 = mock(Function.class);
        Function mockLog3 = mock(Function.class);
        Function mockLog5 = mock(Function.class);
        Function mockLog10 = mock(Function.class);

        when(mockCos.calculate(eq(-Math.PI / 3), anyDouble())).thenReturn(0.5);
        when(mockSin.calculate(eq(-Math.PI / 3), anyDouble())).thenReturn(-0.8660254);

        when(mockLog2.calculate(eq(2.0), anyDouble())).thenReturn(1.0);
        when(mockLog3.calculate(eq(2.0), anyDouble())).thenReturn(0.63092975);
        when(mockLog5.calculate(eq(2.0), anyDouble())).thenReturn(0.43067656);
        when(mockLog10.calculate(eq(2.0), anyDouble())).thenReturn(0.30103);
        when(mockLn.calculate(eq(2.0), anyDouble())).thenReturn(0.69314718);

        Function complete = new CompleteFunction(
                mockCos, mockSin, mockLn, mockLog2, mockLog3, mockLog5, mockLog10
        );

        double resultNegative = complete.calculate(-Math.PI / 3, EPSILON);
        double expectedNegative = 0.5 - (-0.8660254);
        assertEquals(expectedNegative, resultNegative, DELTA);

        double resultPositive = complete.calculate(2.0, EPSILON);

        double log2x = 1.0;
        double log3x = 0.63092975;
        double log5x = 0.43067656;
        double log10x = 0.30103;
        double lnx = 0.69314718;

        double log2xSquared = log2x * log2x;
        double a = ((log5x + log10x) / log2x) * log3x;
        double b = a / (lnx / log3x);
        double c = log2xSquared + log2xSquared;
        double expectedPositive = b - c;

        assertEquals(expectedPositive, resultPositive, DELTA);

        verify(mockCos).calculate(-Math.PI / 3, EPSILON);
        verify(mockSin).calculate(-Math.PI / 3, EPSILON);
        verify(mockLog2).calculate(2.0, EPSILON);
        verify(mockLog3).calculate(2.0, EPSILON);
        verify(mockLog5).calculate(2.0, EPSILON);
        verify(mockLog10).calculate(2.0, EPSILON);
        verify(mockLn).calculate(2.0, EPSILON);
    }

    @Test
    void integrationStepReplaceSinModule() {
        Function realSin = new SinFunction();
        Function mockCos = mock(Function.class);
        Function mockLn = mock(Function.class);
        Function mockLog2 = mock(Function.class);
        Function mockLog3 = mock(Function.class);
        Function mockLog5 = mock(Function.class);
        Function mockLog10 = mock(Function.class);

        when(mockCos.calculate(eq(-Math.PI / 3), anyDouble())).thenReturn(0.5);

        Function complete = new CompleteFunction(
                mockCos, realSin, mockLn, mockLog2, mockLog3, mockLog5, mockLog10
        );

        double result = complete.calculate(-Math.PI / 3, EPSILON);
        double expected = 0.5 - Math.sin(-Math.PI / 3);

        assertEquals(expected, result, DELTA);
    }

    @Test
    void integrationStepReplaceCosModule() {
        Function realSin = new SinFunction();
        Function realCos = new CosFunction(realSin);
        Function mockLn = mock(Function.class);
        Function mockLog2 = mock(Function.class);
        Function mockLog3 = mock(Function.class);
        Function mockLog5 = mock(Function.class);
        Function mockLog10 = mock(Function.class);

        Function complete = new CompleteFunction(
                realCos, realSin, mockLn, mockLog2, mockLog3, mockLog5, mockLog10
        );

        double result = complete.calculate(-Math.PI / 3, EPSILON);
        double expected = Math.cos(-Math.PI / 3) - Math.sin(-Math.PI / 3);

        assertEquals(expected, result, DELTA);
    }

    @Test
    void integrationStepReplaceLnModule() {
        Function realSin = new SinFunction();
        Function realCos = new CosFunction(realSin);
        Function realLn = new LnFunction();
        Function mockLog2 = mock(Function.class);
        Function mockLog3 = mock(Function.class);
        Function mockLog5 = mock(Function.class);
        Function mockLog10 = mock(Function.class);

        when(mockLog2.calculate(eq(2.0), anyDouble())).thenReturn(1.0);
        when(mockLog3.calculate(eq(2.0), anyDouble())).thenReturn(0.6309);
        when(mockLog5.calculate(eq(2.0), anyDouble())).thenReturn(0.4307);
        when(mockLog10.calculate(eq(2.0), anyDouble())).thenReturn(0.3010);

        Function complete = new CompleteFunction(
                realCos, realSin, realLn, mockLog2, mockLog3, mockLog5, mockLog10
        );

        complete.calculate(2.0, EPSILON);

        verify(mockLog2).calculate(2.0, EPSILON);
        verify(mockLog3).calculate(2.0, EPSILON);
        verify(mockLog5).calculate(2.0, EPSILON);
        verify(mockLog10).calculate(2.0, EPSILON);
    }

    @Test
    void integrationStepReplaceAllLogModules() {
        Function realSin = new SinFunction();
        Function realCos = new CosFunction(realSin);
        Function realLn = new LnFunction();
        Function realLog2 = new LogAFunction(2, realLn);
        Function realLog3 = new LogAFunction(3, realLn);
        Function realLog5 = new LogAFunction(5, realLn);
        Function realLog10 = new LogAFunction(10, realLn);

        Function complete = new CompleteFunction(
                realCos, realSin, realLn, realLog2, realLog3, realLog5, realLog10
        );

        double resultNegative = complete.calculate(-Math.PI / 3, EPSILON);
        double expectedNegative = Math.cos(-Math.PI / 3) - Math.sin(-Math.PI / 3);
        assertEquals(expectedNegative, resultNegative, DELTA);

        double resultPositive = complete.calculate(2.0, EPSILON);

        double log2x = Math.log(2.0) / Math.log(2);
        double log3x = Math.log(2.0) / Math.log(3);
        double log5x = Math.log(2.0) / Math.log(5);
        double log10x = Math.log(2.0) / Math.log(10);
        double log2xSquared = log2x * log2x;
        double a = ((log5x + log10x) / log2x) * log3x;
        double b = a / (Math.log(2.0) / log3x);
        double c = log2xSquared + log2xSquared;
        double expectedPositive = b - c;

        assertEquals(expectedPositive, resultPositive, DELTA);
    }

    @Test
    void integrationSpecificPoints() {
        Function realSin = new SinFunction();
        Function realCos = new CosFunction(realSin);
        Function realLn = new LnFunction();
        Function realLog2 = new LogAFunction(2, realLn);
        Function realLog3 = new LogAFunction(3, realLn);
        Function realLog5 = new LogAFunction(5, realLn);
        Function realLog10 = new LogAFunction(10, realLn);

        Function complete = new CompleteFunction(
                realCos, realSin, realLn, realLog2, realLog3, realLog5, realLog10
        );

        double[] testPoints = {
                1.07,
                0.88,
                1.35,
                0,
                -Math.PI / 4,
                -3 * Math.PI / 4,
                -5 * Math.PI / 4
        };

        for (double x : testPoints) {
            double expected;
            if (x <= 0) {
                expected = Math.cos(x) - Math.sin(x);
            } else {
                double log2x = Math.log(x) / Math.log(2);
                double log3x = Math.log(x) / Math.log(3);
                double log5x = Math.log(x) / Math.log(5);
                double log10x = Math.log(x) / Math.log(10);
                double log2xSquared = log2x * log2x;
                double a = ((log5x + log10x) / log2x) * log3x;
                double b = a / (Math.log(x) / log3x);
                double c = log2xSquared + log2xSquared;
                expected = b - c;
            }

            assertEquals(expected, complete.calculate(x, EPSILON), DELTA);
        }
    }
}
package com.math;

import com.back.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CompleteFunctionTest {

    @Mock private Function mockCos;
    @Mock private Function mockSin;
    @Mock private Function mockLn;
    @Mock private Function mockLog2;
    @Mock private Function mockLog3;
    @Mock private Function mockLog5;
    @Mock private Function mockLog10;

    private CompleteFunction completeFunction;
    private final double epsilon = 1e-8;
    private final double delta = 1e-4;

    @BeforeEach
    void setUp() {
        completeFunction = new CompleteFunction(
                mockCos, mockSin, mockLn, mockLog2, mockLog3, mockLog5, mockLog10
        );
    }

    @Test
    void negativeAndZeroValuesShouldUseTrigBranch() {
        when(mockCos.calculate(eq(-1.0), anyDouble())).thenReturn(0.5403);
        when(mockSin.calculate(eq(-1.0), anyDouble())).thenReturn(-0.8415);
        when(mockCos.calculate(eq(-0.5), anyDouble())).thenReturn(0.8776);
        when(mockSin.calculate(eq(-0.5), anyDouble())).thenReturn(-0.4794);
        when(mockCos.calculate(eq(0.0), anyDouble())).thenReturn(1.0);
        when(mockSin.calculate(eq(0.0), anyDouble())).thenReturn(0.0);

        double result1 = completeFunction.calculate(-1.0, epsilon);
        assertEquals(0.5403 - (-0.8415), result1, delta);

        double result2 = completeFunction.calculate(-0.5, epsilon);
        assertEquals(0.8776 - (-0.4794), result2, delta);

        double result3 = completeFunction.calculate(0.0, epsilon);
        assertEquals(1.0 - 0.0, result3, delta);

        verify(mockCos, atLeastOnce()).calculate(anyDouble(), anyDouble());
        verify(mockSin, atLeastOnce()).calculate(anyDouble(), anyDouble());
        verify(mockLog2, never()).calculate(anyDouble(), anyDouble());
        verify(mockLog3, never()).calculate(anyDouble(), anyDouble());
        verify(mockLog5, never()).calculate(anyDouble(), anyDouble());
        verify(mockLog10, never()).calculate(anyDouble(), anyDouble());
        verify(mockLn, never()).calculate(anyDouble(), anyDouble());
    }

    @Test
    void positiveValuesShouldMatchReferenceFormula() {
        when(mockLog2.calculate(eq(2.0), anyDouble())).thenReturn(1.0);
        when(mockLog3.calculate(eq(2.0), anyDouble())).thenReturn(0.6309);
        when(mockLog5.calculate(eq(2.0), anyDouble())).thenReturn(0.4307);
        when(mockLog10.calculate(eq(2.0), anyDouble())).thenReturn(0.3010);
        when(mockLn.calculate(eq(2.0), anyDouble())).thenReturn(0.6931);

        when(mockLog2.calculate(eq(3.0), anyDouble())).thenReturn(1.5850);
        when(mockLog3.calculate(eq(3.0), anyDouble())).thenReturn(1.0);
        when(mockLog5.calculate(eq(3.0), anyDouble())).thenReturn(0.6826);
        when(mockLog10.calculate(eq(3.0), anyDouble())).thenReturn(0.4771);
        when(mockLn.calculate(eq(3.0), anyDouble())).thenReturn(1.0986);

        double result1 = completeFunction.calculate(2.0, epsilon);
        double log2x = 1.0;
        double log3x = 0.6309;
        double log5x = 0.4307;
        double log10x = 0.3010;
        double lnx = 0.6931;
        double log2xSquared = log2x * log2x;
        double a = ((log5x + log10x) / log2x) * log3x;
        double b = a / (lnx / log3x);
        double c = log2xSquared + log2xSquared;
        double expected1 = b - c;
        assertEquals(expected1, result1, delta);

        double result2 = completeFunction.calculate(3.0, epsilon);
        log2x = 1.5850;
        log3x = 1.0;
        log5x = 0.6826;
        log10x = 0.4771;
        lnx = 1.0986;
        log2xSquared = log2x * log2x;
        a = ((log5x + log10x) / log2x) * log3x;
        b = a / (lnx / log3x);
        c = log2xSquared + log2xSquared;
        double expected2 = b - c;
        assertEquals(expected2, result2, delta);

        verify(mockLog2, atLeastOnce()).calculate(anyDouble(), anyDouble());
        verify(mockLog3, atLeastOnce()).calculate(anyDouble(), anyDouble());
        verify(mockLog5, atLeastOnce()).calculate(anyDouble(), anyDouble());
        verify(mockLog10, atLeastOnce()).calculate(anyDouble(), anyDouble());
        verify(mockLn, atLeastOnce()).calculate(anyDouble(), anyDouble());
    }

    @Test
    void xEqualsOneShouldThrow() {
        lenient().when(mockLog2.calculate(eq(1.0), anyDouble())).thenReturn(0.0);

        assertThrows(ArithmeticException.class, () -> completeFunction.calculate(1.0, epsilon));
    }
}
package com.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.stub.SinStub;

public class FunctionTest {

    private final SinFunction sin = new SinFunction();
    private final double eps = 1e-6;

    @Test
    public void testSIN() {
        SinStub sinStub = new SinStub();

        for (double[] point: sinStub.getTable()) {
            double x = point[0];
            assertEquals(Math.sin(x), sin.calculate(x, eps), eps,
                    "sin(" + x + ") должен совпадать с Math.sin");
        }
    }

    @Test
    public void testCOSWithStub() {
        SinStub sinStub = new SinStub();
        CosFunction cosWithStub = new CosFunction(sin);

        for (double[] point : sinStub.getTable()) {
            double x = point[0];
            assertEquals(Math.cos(x), cosWithStub.calculate(x, eps), eps,
                    "cos(" + x + ")  должен совпадать с Math.cos");
        }
    }
}
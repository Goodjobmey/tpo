package com.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.stub.SinStub;

public class FunctionTest {

    private final SinFunction sin = new SinFunction();
    private final double eps = 1e-6;

    // тест синуса через реальную функцию
    @Test
    public void testSIN() {
        double[] inputs = {
                0,
                Math.PI / 6,
                2 * Math.PI / 3,
                4 * Math.PI / 3,
                11 * Math.PI / 6,
                -Math.PI / 4,
                10 * Math.PI + Math.PI / 4,
                1e-10
        };

        for (double x : inputs) {
            assertEquals(Math.sin(x), sin.calculate(x, eps), eps,
                    "sin(" + x + ") должен совпадать с Math.sin");
        }
    }

    // тест косинуса через заглушку синуса
    @Test
    public void testCOSWithStub() {
        SinStub sinStub = new SinStub();  // заглушка
        CosFunction cosWithStub = new CosFunction(sinFunction);

//        double[] inputs = {
//                0,
//                Math.PI / 6,
//                Math.PI / 2,
//                2 * Math.PI / 3,
//                Math.PI,
//                4 * Math.PI / 3,
//                11 * Math.PI / 6,
//                -Math.PI / 4,
//                10 * Math.PI + Math.PI / 4,
//                1e-10
//        };

        for (double x : sinStub) {
            assertEquals(Math.cos(x), cosWithStub.calculate(x, eps), eps,
                    "cos(" + x + ") должен совпадать с Math.cos через stub");
        }
    }
}
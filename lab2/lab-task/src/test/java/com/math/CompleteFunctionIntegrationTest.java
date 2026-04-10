package com.math;

import com.back.Function;
import com.stub.CosStub;
import com.stub.LnStub;
import com.stub.LogAStub;
import com.stub.SinStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompleteFunctionIntegrationTest {

    private static final double EPSILON = 1e-6;
    private static final double DELTA = 1e-6;

    @Test
    void integrationStepAllStubs() {
        Function sin = new SinStub();
        Function cos = new CosStub();
        Function ln = new LnStub();
        Function log2 = new LogAStub(2);
        Function log3 = new LogAStub(3);
        Function log5 = new LogAStub(5);
        Function log10 = new LogAStub(10);
        Function complete = new CompleteFunction(cos, sin, ln, log2, log3, log5, log10);

        assertEquals(calcExpected(-Math.PI / 3, cos, sin, ln, log2, log3, log5, log10),
                complete.calculate(-Math.PI / 3, EPSILON), DELTA);
        assertEquals(calcExpected(2.0, cos, sin, ln, log2, log3, log5, log10),
                complete.calculate(2.0, EPSILON), DELTA);
    }

    @Test
    void integrationStepReplaceSinModule() {
        Function sin = new SinFunction();
        Function cos = new CosStub();
        Function ln = new LnStub();
        Function log2 = new LogAStub(2);
        Function log3 = new LogAStub(3);
        Function log5 = new LogAStub(5);
        Function log10 = new LogAStub(10);
        Function complete = new CompleteFunction(cos, sin, ln, log2, log3, log5, log10);

        assertEquals(calcExpected(-Math.PI / 3, cos, sin, ln, log2, log3, log5, log10),
                complete.calculate(-Math.PI / 3, EPSILON), DELTA);
    }

    @Test
    void integrationStepReplaceCosModule() {
        Function sin = new SinFunction();
        Function cos = new CosFunction(sin);
        Function ln = new LnStub();
        Function log2 = new LogAStub(2);
        Function log3 = new LogAStub(3);
        Function log5 = new LogAStub(5);
        Function log10 = new LogAStub(10);
        Function complete = new CompleteFunction(cos, sin, ln, log2, log3, log5, log10);

        assertEquals(calcExpected(-Math.PI / 3, cos, sin, ln, log2, log3, log5, log10),
                complete.calculate(-Math.PI / 3, EPSILON), DELTA);
    }

    @Test
    void integrationStepReplaceLnModule() {
        Function sin = new SinFunction();
        Function cos = new CosFunction(sin);
        Function ln = new LnFunction();
        Function log2 = new LogAStub(2);
        Function log3 = new LogAStub(3);
        Function log5 = new LogAStub(5);
        Function log10 = new LogAStub(10);
        Function complete = new CompleteFunction(cos, sin, ln, log2, log3, log5, log10);

        assertEquals(calcExpected(2.0, cos, sin, ln, log2, log3, log5, log10),
                complete.calculate(2.0, EPSILON), DELTA);
    }

    @Test
    void integrationStepReplaceAllLogModules() {
        Function sin = new SinFunction();
        Function cos = new CosFunction(sin);
        Function ln = new LnFunction();
        Function log2 = new LogAFunction(2, ln);
        Function log3 = new LogAFunction(3, ln);
        Function log5 = new LogAFunction(5, ln);
        Function log10 = new LogAFunction(10, ln);
        Function complete = new CompleteFunction(cos, sin, ln, log2, log3, log5, log10);

        assertEquals(calcExpected(2.0, cos, sin, ln, log2, log3, log5, log10),
                complete.calculate(2.0, EPSILON), DELTA);
    }

    @Test
    void integrationSpecificPoints() {
        Function sin = new SinFunction();
        Function cos = new CosFunction(sin);
        Function ln = new LnFunction();
        Function log2 = new LogAFunction(2, ln);
        Function log3 = new LogAFunction(3, ln);
        Function log5 = new LogAFunction(5, ln);
        Function log10 = new LogAFunction(10, ln);
        Function complete = new CompleteFunction(cos, sin, ln, log2, log3, log5, log10);

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
            assertEquals(
                    calcExpected(x, cos, sin, ln, log2, log3, log5, log10),
                    complete.calculate(x, EPSILON),
                    DELTA
            );
        }
    }

    private double calcExpected(
            double x,
            Function cos,
            Function sin,
            Function ln,
            Function log2,
            Function log3,
            Function log5,
            Function log10
    ) {
        if (x <= 0) {
            return cos.calculate(x, EPSILON) - sin.calculate(x, EPSILON);
        }

        double log2x = log2.calculate(x, EPSILON);
        double log3x = log3.calculate(x, EPSILON);
        double log2xSquared = log2x * log2x;
        double a = ((log5.calculate(x, EPSILON) + log10.calculate(x, EPSILON)) / log2x) * log3x;
        double b = a / (ln.calculate(x, EPSILON) / log3x);
        double c = log2xSquared + log2xSquared;
        return b - c;
    }


}

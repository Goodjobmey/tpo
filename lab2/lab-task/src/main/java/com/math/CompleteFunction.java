package com.math;

import com.back.Function;

public class CompleteFunction implements Function {

    private final Function cosFunction;
    private final Function sinFunction;
    private final Function lnFunction;
    private final Function log2;
    private final Function log3;
    private final Function log5;
    private final Function log10;

    public CompleteFunction() {
        this.sinFunction = new SinFunction();
        this.cosFunction = new CosFunction(this.sinFunction);
        this.lnFunction = new LnFunction();

        this.log2 = new LogAFunction(2, this.lnFunction);
        this.log3 = new LogAFunction(3, this.lnFunction);
        this.log5 = new LogAFunction(5, this.lnFunction);
        this.log10 = new LogAFunction(10, this.lnFunction);
    }

    public CompleteFunction(Function cosFunction, Function sinFunction,
                            Function lnFunction, Function log2Function,
                            Function log3Function, Function log5Function,
                            Function log10Function) {
        this.cosFunction = cosFunction;
        this.sinFunction = sinFunction;
        this.lnFunction = lnFunction;
        this.log2 = log2Function;
        this.log3 = log3Function;
        this.log5 = log5Function;
        this.log10 = log10Function;
    }

    @Override
    public double calculate(double x, double epsilon) {
        if (x <= 0) {
            return cosFunction.calculate(x, epsilon) - sinFunction.calculate(x, epsilon);
        }

        if (Math.abs(x - 1.0) < 1e-12) {
            throw new ArithmeticException("division by zero at x = 1");
        }

        double log2x = log2.calculate(x, epsilon);
        double log3x = log3.calculate(x, epsilon);
        double log2xSquared = log2x * log2x;
        double a = ((log5.calculate(x, epsilon) + log10.calculate(x, epsilon)) / log2x) * log3x;
        double b = a / (lnFunction.calculate(x, epsilon) / log3x);
        double c = log2xSquared + log2xSquared;

        return b - c;
    }
}

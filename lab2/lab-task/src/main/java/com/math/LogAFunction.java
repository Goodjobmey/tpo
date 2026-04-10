package com.math;

import com.back.Function;

public class LogAFunction implements Function {

    private final Function lnFunction;
    private final double base;
    private final double lnA;

    public LogAFunction(double base, Function lnFunction) {
        if (base <= 0 || Math.abs(base - 1.0) < 1e-12) {
            throw new IllegalArgumentException("base must be > 0 and != 1");
        }
        this.base = base;
        this.lnFunction = lnFunction;
        this.lnA = lnFunction.calculate(base, 1e-15);
    }

    public double getBase() {
        return base;
    }

    @Override
    public double calculate(double x, double epsilon) {
        double lnX = lnFunction.calculate(x, epsilon);
        return lnX / lnA;
    }
}

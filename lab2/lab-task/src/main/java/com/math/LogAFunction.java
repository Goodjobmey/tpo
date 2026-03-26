package com.math;

import com.back.Function;

public class LogAFunction implements Function {

    private final LnFunction lnFunction;
    private final double base;
    private final double lnA;

    public LogAFunction(double base, LnFunction lnFunction) {
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

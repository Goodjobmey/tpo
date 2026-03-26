package com.math;

import com.back.Function;

public class CompleteFunction implements Function {

    private final CosFunction cosFunction;
    private final SinFunction sinFunction;
    private final LnFunction lnFunction;
    private final LogAFunction log2;
    private final LogAFunction log3;
    private final LogAFunction log5;
    private final LogAFunction log10;

    public CompleteFunction() {
        this.sinFunction = new SinFunction();
        this.cosFunction = new CosFunction(this.sinFunction);
        this.lnFunction = new LnFunction();

        this.log2 = new LogAFunction(2, this.lnFunction);
        this.log3 = new LogAFunction(3, this.lnFunction);
        this.log5 = new LogAFunction(5, this.lnFunction);
        this.log10 = new LogAFunction(10, this.lnFunction);
    }

    public CompleteFunction(CosFunction cosFunction, SinFunction sinFunction,
                          LnFunction lnFunction, LogAFunction log2Function,
                          LogAFunction log3Function, LogAFunction log5Function,
                          LogAFunction log10Function) {
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
        if (x <= 0){
            return cosFunction.calculate(x, epsilon) - sinFunction.calculate(x, epsilon);
        }else{
            if (Math.abs(x - 1.0) < 1e-12) {
                throw new ArithmeticException("делитель равно нулю запрещено");
            }
            double log2xSquared = log2.calculate(x, epsilon) * log2.calculate(x, epsilon);
            double A = ((log5.calculate(x, epsilon) + log10.calculate(x, epsilon)) / log2.calculate(x, epsilon)) * log3.calculate(x, epsilon);
            double B = A / (lnFunction.calculate(x, epsilon) / log3.calculate(x, epsilon));
            double C = log2xSquared + log2xSquared;
            return B - C;
        }
    }
}

package com.math;

import com.back.Function;

public class CosFunction implements Function {

    private final SinFunction sinFunction;

    public CosFunction() {
        this.sinFunction = new SinFunction();
    }

    public CosFunction(SinFunction sinFunction) {
        this.sinFunction = sinFunction;
    }

    @Override
    public double calculate(double x, double epsilon) {
        double cosX = x + Math.PI / 2;

        double cycle = 2 * Math.PI;
        cosX = cosX % cycle;
        if (cosX > Math.PI) {
            cosX = cosX - cycle;
        } else if (cosX < -Math.PI) {
            cosX = cosX + cycle;
        }

        return sinFunction.calculate(cosX, epsilon);
    }
}

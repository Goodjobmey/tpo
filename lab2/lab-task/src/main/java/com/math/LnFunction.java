package com.math;

import com.back.Function;

public class LnFunction implements Function {

    @Override
    public double calculate(double x, double epsilon) {
        if (x <= 0) {
            throw new IllegalArgumentException("x должна больше чем нулей");
        }

        double input = x;
        double sign = 1;
        if (x > 1) {
            input = 1 / x;
            sign = -1;
        }

        double y = (input - 1) / (input + 1);
        double ySquared = y * y;

        double result = 0;
        double term = y;
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            result += term;

            term = term * ySquared;
            n += 2;
            term = term * (n - 2) / n;
        }

        return sign * 2 * result;
    }

}

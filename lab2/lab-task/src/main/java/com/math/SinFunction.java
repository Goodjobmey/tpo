package com.math;

import com.back.Function;

public class SinFunction implements Function {

    @Override
    public double calculate(double x, double epsilon) {
        double input = x;
        double sign = 1;
        if (x < 0) {
            input = -x;
            sign = -1;
        }

        double cycle = 2 * Math.PI;
        input = input % cycle;

        if (input > cycle) {
            input -= cycle;
        }

        double result = 0;
        double term = input;
        int n = 1;
        int signTerm = 1;

        while (Math.abs(term) >= epsilon) {
            result += signTerm * term;
            term = term * input * input / ((n + 1) * (n + 2));
            signTerm = -signTerm;
            n += 2;
        }

        return sign * result;
    }
}

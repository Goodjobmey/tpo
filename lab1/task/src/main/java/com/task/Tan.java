package com.task;

public class Tan {

    public static double calculateTan(double x, int terms){
        if (Double.isNaN(x)) {
            return Double.NaN;
        }

        if (Double.isInfinite(x)) {
            return Double.NaN;
        }

        if (x == 0.0) {
            return x;
        }

        if (Math.abs(x) > 1e6) {
            return Double.NaN;
        }

        if (Math.abs(x) >= Math.PI / 2) {
            if (Math.abs(Math.abs(x) - Math.PI / 2) < 1e-12) {
                return Double.POSITIVE_INFINITY;
            }
            throw new IllegalArgumentException("|x| должно меньше чем π/2");
        }

        if (terms < 1) {
            throw new IllegalArgumentException("Число развернутых членов должно быть больше или равно 1");
        }

        double result = 0.0;
        for (int n = 1; n <= terms; n++) {
            double coefficient = calculateTanCoefficient(n);
            double xPower = Math.pow(x, 2 * n - 1);
            result += coefficient * xPower;
        }
        return result;
    }

    private static double calculateTanCoefficient(int n) {
        switch (n) {
            case 1: return 1.0;
            case 2: return 1.0 / 3.0;
            case 3: return 2.0 / 15.0;
            case 4: return 17.0 / 315.0;
            default:
                double coeff = 17.0 / 315.0;
                for (int i = 5; i <= n; i++) {
                    coeff = coeff * (2.0 * i - 3) * (2.0 * i - 3) / ((2.0 * i - 2) * (2.0 * i - 1));
                }
                return coeff;
        }
    }
}

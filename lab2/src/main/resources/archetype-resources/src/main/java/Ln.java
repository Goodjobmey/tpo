public class Ln {

    public static double calculate(double x, double eps) {
        if (x <= 0) return Double.NaN;

        double y = (x - 1) / (x + 1);
        double term = y;
        double sum = 0;
        int n = 1;

        while (Math.abs(term) > eps) {
            sum += term / n;
            term *= y * y;
            n += 2;
        }

        return 2 * sum;
    }
}
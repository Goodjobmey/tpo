public class Sin {

    public static double calculate(double x, double eps) {
        double term = x;
        double sum = x;
        int n = 1;

        while (Math.abs(term) > eps) {
            term *= -1 * x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }
}
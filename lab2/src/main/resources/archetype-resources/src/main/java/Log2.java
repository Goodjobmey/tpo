public class Log2 {

    public static double calculate(double x, double eps) {
        return Ln.calculate(x, eps) / Ln.calculate(2, eps);
    }
}
public class Log5 {
    public static double calculate(double x, double eps) {
        return Ln.calculate(x, eps) / Ln.calculate(5, eps);
    }
}
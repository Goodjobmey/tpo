public class Log3 {
    public static double calculate(double x, double eps) {
        return Ln.calculate(x, eps) / Ln.calculate(3, eps);
    }
}
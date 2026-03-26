public class Cos {

    public static double calculate(double x, double eps) {
        return Sin.calculate(x + Math.PI / 2, eps);
    }
}
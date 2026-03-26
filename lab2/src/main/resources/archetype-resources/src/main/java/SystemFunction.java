public class SystemFunction {

    public static double calculate(double x, double eps) {

        if (x <= 0) {
            double cos = Cos.calculate(x, eps);
            double sin = Sin.calculate(x, eps);
            return cos - sin;
        }

        if (x == 1) {
            return Double.NaN;
        }

        double log2 = Log2.calculate(x, eps);
        double log3 = Log3.calculate(x, eps);
        double log5 = Log5.calculate(x, eps);
        double log10 = Log10.calculate(x, eps);
        double ln = Ln.calculate(x, eps);

        double part1 = (log5 + log10) / log2;
        double part2 = (log3 * log3) / ln;

        return part1 * part2 - 2 * log2 * log2;
    }
}
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public static void write(double start, double end, double step, double eps) throws IOException {
        FileWriter writer = new FileWriter("output.csv");

        writer.write("x;result\n");

        for (double x = start; x <= end; x += step) {
            double y = SystemFunction.calculate(x, eps);
            writer.write(x + ";" + y + "\n");
        }

        writer.close();
    }
}
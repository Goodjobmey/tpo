package com.util;

import com.back.Function;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVOutput {

    private static String delimiter = ",";

    public static void setDelimiter(String delim) {
        delimiter = delim;
    }

    public static String getDelimiter() {
        return delimiter;
    }

    public static void export(Function function, double startX, double endX,
                              double step, double epsilon, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("x" + delimiter + "result");
            for (double x = startX; x <= endX + step/2; x += step) {
                try {
                    double result = function.calculate(x, epsilon);
                    writer.printf("%.6f" + delimiter + "%.6f%n", x, result);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            System.out.println("save in: " + filename);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
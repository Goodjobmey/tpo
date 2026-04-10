package com.back;

import com.math.CompleteFunction;
import com.math.CosFunction;
import com.math.LnFunction;
import com.math.LogAFunction;
import com.math.SinFunction;
import com.util.CSVOutput;

public class Main {

    public static void main(String[] args) {
        if (args.length < 6 || args.length > 7) {
            printUsage();
            return;
        }

        String moduleName = args[0].toLowerCase();
        double startX = Double.parseDouble(args[1]);
        double endX = Double.parseDouble(args[2]);
        double step = Double.parseDouble(args[3]);
        double epsilon = Double.parseDouble(args[4]);
        String filename = args[5];
        String delimiter = args.length == 7 ? args[6] : ",";

        if (step <= 0) {
            throw new IllegalArgumentException("step must be > 0");
        }
        if (epsilon <= 0) {
            throw new IllegalArgumentException("epsilon must be > 0");
        }

        CSVOutput.setDelimiter(delimiter);
        CSVOutput.export(buildModule(moduleName), startX, endX, step, epsilon, filename);
    }

    private static Function buildModule(String moduleName) {
        LnFunction ln = new LnFunction();
        return switch (moduleName) {
            case "sin" -> new SinFunction();
            case "cos" -> new CosFunction();
            case "ln" -> ln;
            case "log2" -> new LogAFunction(2, ln);
            case "log3" -> new LogAFunction(3, ln);
            case "log5" -> new LogAFunction(5, ln);
            case "log10" -> new LogAFunction(10, ln);
            case "complete" -> new CompleteFunction();
            default -> throw new IllegalArgumentException("unknown module: " + moduleName);
        };
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java ... com.back.Main <module> <startX> <endX> <step> <epsilon> <file> [delimiter]");
        System.out.println("Modules: sin, cos, ln, log2, log3, log5, log10, complete");
    }
}

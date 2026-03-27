package com.stub;

import com.back.Function;
import java.util.*;

public class TableStub implements Function {
    private final List<double[]> table = new ArrayList<>();
    private final String name;
    private boolean sorted = false;

    public TableStub(String name) {
        this.name = name;
    }

    public void addPoint(double x, double y) {
        table.add(new double[]{x, y});
        sorted = false;
    }

    public void clear() {
        table.clear();
        sorted = false;
    }

    private void sort() {
        if (!sorted && !table.isEmpty()) {
            table.sort(Comparator.comparingDouble(a -> a[0]));
            sorted = true;
        }
    }

    @Override
    public double calculate(double x, double epsilon) {
        sort();

        for (double[] point : table) {
            if (Math.abs(point[0] - x) < 1e-12) {
                return point[1];
            }
        }

        for (int i = 0; i < table.size() - 1; i++) {
            double x1 = table.get(i)[0];
            double x2 = table.get(i + 1)[0];

            if (x1 <= x && x <= x2) {
                double y1 = table.get(i)[1];
                double y2 = table.get(i + 1)[1];

                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        throw new RuntimeException("x out of range");
    }

    public String getName() {
        return name;
    }

    public List<double[]> getTable() {
        sort();
        return table;
    }

    public int size() {
        return table.size();
    }
}
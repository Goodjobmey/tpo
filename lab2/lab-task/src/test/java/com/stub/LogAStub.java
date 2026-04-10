package com.stub;

public class LogAStub extends TableStub {
    private final double base;

    public LogAStub(double base) {
        super("Log" + base);
        this.base = base;

        if (base == 2) {
            addPoint(1.0, 0.0);
            addPoint(2.0, 1.0);
            addPoint(4.0, 2.0);
            addPoint(8.0, 3.0);
            addPoint(16.0, 4.0);
            addPoint(0.5, -1.0);
            addPoint(0.25, -2.0);
        } else if (base == 3) {
            addPoint(1.0, 0.0);
            addPoint(3.0, 1.0);
            addPoint(9.0, 2.0);
            addPoint(27.0, 3.0);
            addPoint(1.0/3, -1.0);
        } else if (base == 5) {
            addPoint(1.0, 0.0);
            addPoint(5.0, 1.0);
            addPoint(25.0, 2.0);
            addPoint(125.0, 3.0);
        } else if (base == 10) {
            addPoint(1.0, 0.0);
            addPoint(10.0, 1.0);
            addPoint(100.0, 2.0);
            addPoint(1000.0, 3.0);
            addPoint(0.1, -1.0);
            addPoint(0.01, -2.0);
        }
    }

    public double getBase() {
        return base;
    }
}
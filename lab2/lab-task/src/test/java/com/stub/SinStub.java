package com.stub;

public class SinStub extends TableStub {

    public SinStub() {
        super("sin");

        addPoint(0.0, 0.0);
        addPoint(Math.PI / 6, 0.5);
        addPoint(Math.PI / 4, Math.sqrt(2) / 2);
        addPoint(Math.PI / 3, Math.sqrt(3) / 2);
        addPoint(Math.PI / 2, 1.0);
        addPoint(2 * Math.PI / 3, Math.sqrt(3) / 2);
        addPoint(3 * Math.PI / 4, Math.sqrt(2) / 2);
        addPoint(5 * Math.PI / 6, 0.5);
        addPoint(Math.PI, 0.0);
        addPoint(7 * Math.PI / 6, -0.5);
        addPoint(5 * Math.PI / 4, -Math.sqrt(2) / 2);
        addPoint(4 * Math.PI / 3, -Math.sqrt(3) / 2);
        addPoint(3 * Math.PI / 2, -1.0);
        addPoint(5 * Math.PI / 3, -Math.sqrt(3) / 2);
        addPoint(7 * Math.PI / 4, -Math.sqrt(2) / 2);
        addPoint(2 * Math.PI, 0.0);

        addPoint(-Math.PI / 6, -0.5);
        addPoint(-Math.PI / 4, -Math.sqrt(2) / 2);
        addPoint(-Math.PI / 3, -Math.sqrt(3) / 2);
        addPoint(-Math.PI / 2, -1.0);
        addPoint(-2 * Math.PI / 3, -Math.sqrt(3) / 2);
        addPoint(-3 * Math.PI / 4, -Math.sqrt(2) / 2);
        addPoint(-5 * Math.PI / 6, -0.5);
        addPoint(-Math.PI, 0.0);
        addPoint(-3 * Math.PI / 2, 1.0);
        addPoint(-2 * Math.PI, 0.0);
    }
}
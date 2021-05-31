package impl;

import interfaces.Calc;

public class Minus implements Calc {
    @Override
    public double calculate(double i, double j) {
        return i - j;
    }
}
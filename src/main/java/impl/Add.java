package impl;

import interfaces.Calc;

public class Add implements Calc {
    private double count = 0;
    @Override
    public double add(double i, double j) {
        count = count + i;
        count = count + j;
        return count;
    }
}

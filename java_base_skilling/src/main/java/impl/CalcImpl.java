package impl;

import abstracts.CalcAb;
import interfaces.Calc;

public class CalcImpl extends CalcAb {


    @Override
    public double calculate(double i, double j) {
        return 0;
    }

    public static void main(String[] args) {

        Calc calc = new CalcImpl();
        double add = calc.calculate(10, 13);
        System.out.println(add);


    }

}

package test;

import interfaces.Calc;

import java.util.ServiceLoader;

public class test {

    public static void main(String[] args) {

        ServiceLoader<Calc> load = ServiceLoader.load(Calc.class);
        for (Calc calc : load) {
            double calculate = calc.calculate(10, 1);
            System.out.println(calculate);
        }


    }
}

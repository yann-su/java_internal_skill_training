package lambdas;

import interfaces.functional.CalcLambda;

public class CustomFunctional {

    public static void main(String[] args) {

        CalcLambda calcLambda =  (a, b) -> {return a + b;};
        CalcLambda calcLambda1 = Integer::sum;

        System.out.println(calcLambda.calc(10,1));
        System.out.println(calcLambda1.calc(131,23));


    }
}

package debug;

import impl.Add;
import interfaces.Calc;

public class BreakPoint {
    
    
    public static void line(){
        System.out.println("this is the line break point");
    }
    
    public static void detailLine(){
        System.out.println("this is detail line break point");
    }
    
    public static void method(){
        System.out.println();
        Calc calc = new Add();
        calc.add(10,11);
    }
    
    public static void exception(){
        Object o = null;
        o.toString();
        System.out.println(o.getClass().getTypeName());
    }

    public static void main(String[] args) {
//        line();
//        detailLine();
//        method();
        exception();
    }
    
}


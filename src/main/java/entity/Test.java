package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test {

    public static void main(String[] args) {



        List<? extends Fruit> list1 = new ArrayList();
        Object orange = list1.get(0);


    }

    public static  <IN extends Number> void testExtends(IN in){
        System.out.println(in);
    }
}


class Fruit{

}


class Orange extends Fruit{

}


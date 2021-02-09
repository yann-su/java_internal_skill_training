package internal.fuction;

import interfaces.Calc;

import java.util.*;

public class Anonymous {

    public static void main(String[] args) {


        /**
         *  排序匿名函数
         *  举例： 排序List
         */

        List<Integer> ages = new ArrayList<>();
        ages.add(11);
        ages.add(12);
        ages.add(10);
        ages.add(40);
        ages.add(20);
        ages.add(8);

//        Collections.sort(ages, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
        //如上代码是匿名函数，如下代码为lambda表达式
        Comparator<Integer> comparator = (t1, t2) -> t1.compareTo(t2);
        Collections.sort(ages, comparator);
        System.out.println(ages);


        //传统方式
        Calc calc = new Calc() {
            @Override
            public double add(double i, double j) {
                return i + j;
            }
        };
        double add = calc.add(10, 13);
        System.out.println(add);

        //第二种使用lambda表达式进行匿名操作
        Calc calc1 = (double i, double j) -> i + j;
        System.out.println(calc1.add(1, 2));


    }

}

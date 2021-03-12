package enums;

import  enums.SpicinessEnum.*;

public class StaticEnums {

    SpicinessEnum degree;

    public StaticEnums(SpicinessEnum degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "this is " + degree;
    }


    public static void main(String[] args) {
        //非static 需要
//        System.out.println(new StaticEnums(SpicinessEnum.HOT));
        //static 可以不引用包就可以使用实例
        System.out.println(new StaticEnums(SpicinessEnum.HOT));
    }
}

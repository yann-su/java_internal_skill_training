package operator;

public class BoleanIsEqual {
    public static void main(String[] args) {
        Integer integer = 1;
        Integer integer1 = 1;
        System.out.println(integer == integer1);
        System.out.println(integer != integer1);

        Value value = new Value(10);
        Value value1 = new Value(10);
        //取决于equals的内置函数的比较方式，当然也可以重写equals，但是一般重写equals hascode 一般也是要重写的，因为在判断对象是不是相同时候是取hashcode的方式，以防止hashMap
        System.out.println(value.integer.equals(value1.integer));
        System.out.println(value.equals(value1)); //想要相等重写equals即可




    }

}


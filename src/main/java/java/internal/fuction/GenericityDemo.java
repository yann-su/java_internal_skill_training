package java.internal.fuction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericityDemo<T> {

    String namespace;
    //泛型数组
    T[] e = (T[])new Object[10];

    String name;

    T Hours;

    public T returenHours(T t) {
        return t;
    }

    //
    public List<T> copy1(T[] e){
        ArrayList<T> list = new ArrayList<>();
        for (T e1 : e){
            list.add(e1);
        }
        return list;
    }

    public static <E> List<E> copy(E[] e){
        ArrayList<E> list = new ArrayList<>();
        for (E e1 : e){
            list.add(e1);
        }
        return list;
    }

    public static void main(String[] args) {

        //泛型定义后，如果不使用指明类的泛型
        GenericityDemo<Person> objectgenericityDemo = new GenericityDemo<>();
        Person person = objectgenericityDemo.returenHours(new Person());
        System.out.println(person);
        GenericityDemo2 genericityDemo2 = new GenericityDemo2();
        genericityDemo2.returenHours("1231");

        GenericityDemo3 genericityDemo3 = new GenericityDemo3();
        genericityDemo3.returenHours(10);
//        genericityDemo3.returenHours("adaf");  //Integer
        GenericityDemo4<Integer> genericityDemo4 = new GenericityDemo4();
        genericityDemo4.returenHours(123);


        List<Integer> arrayList = new ArrayList<>();
        List<String> arrayList2 = new ArrayList<>();
        System.out.println(arrayList.getClass() == arrayList2.getClass());

        //注意点，异常类不能是泛型


        Integer[] integer = new Integer[]{1,2,3,4,5,6};
        List<Integer> copy = GenericityDemo.copy(integer);
        System.out.println(copy);

    }

}



//子对象继承泛型基类，不指定会自动格式化成Object
class GenericityDemo2 extends GenericityDemo{


}
//子对象继承泛型基类，继承中指定类型，只能操作这个类型
class GenericityDemo3 extends GenericityDemo<Integer>{



}
//子对象继承泛型基类，继承中再指定泛型，则可以在子类中继续使用泛型
class GenericityDemo4<T> extends GenericityDemo<T>{


}

class Person{
    @Override
    public String toString() {
        return "this is person";
    }
}

class Student extends Person{
    @Override
    public String toString() {
        return "this is student";
    }
}


class GenericityTest{

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new LinkedList<>();
        //反证法求证，此数据是不能通过编译的
//        list = list2;
        //底层在实现逻辑上，是一致的，导致在编译的时候，代码是通过的
        boolean isLike = list.getClass() == list2.getClass();
        System.out.println(isLike);

        List<Student> list4 = new ArrayList<>();
        list4.add(new Student());
        List<Person> list5 = new ArrayList<>();
        list5.add(new Person());
        List<Object> list6 = new ArrayList<>();

        List<? extends Person> lists = null;
//        lists.add(new Student())
        List<? super Person> listp = null;



        lists = list4;
        lists = list5;
//        lists = list6;
//        lists.add(new Student()); //协变范围变化，在编译器转化的时候，不知道给定什么类型，jdk 1.5中形参和返回类型必须一致
//        lists.add(new Object());

//        listp = list4;
        listp = list5;
        listp = list6;
        listp.add(new Student());
        listp.add(new Person());
//        listp.add(new Object());//逆变导致范围更变

        listp.add(new Student());


        System.out.println(lists.get(0));


    }

}


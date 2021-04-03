package streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class StreamMap {

    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        list.add(1310);
        list.add(231231);
        list.add("张三");

        list.forEach(System.out::println);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1311,413);
        objectObjectHashMap.put(231,313);
        objectObjectHashMap.put(21313,3141);
        objectObjectHashMap.put(3114,241241);

        objectObjectHashMap.entrySet().stream().forEach(System.out::println);
        objectObjectHashMap.keySet().forEach(System.out::println);

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 45, 3, 5);
        integerStream.forEach((x)->{
            System.out.println(x);
        });

        //filter 保留需要的
        list.stream().filter(x->x.toString().startsWith("张")).forEach(System.out::println);
        System.out.println("-------------");
        //skip //和limit是互斥的关系
        list.stream().skip(2).forEach(System.out::println);
        System.out.println("-------------");
        //limit 同sql语法
        list.stream().limit(2).forEach(System.out::println);








    }

}

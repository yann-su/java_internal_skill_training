package streams;

import java.util.ArrayList;
import java.util.HashMap;

public class StreamMap {

    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        list.add(1310);
        list.add(231231);
        list.add("张三");

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1311,413);
        objectObjectHashMap.put(231,313);
        objectObjectHashMap.put(21313,3141);
        objectObjectHashMap.put(3114,241241);

        objectObjectHashMap.entrySet().stream().forEach(System.out::println);
        objectObjectHashMap.keySet().forEach(System.out::println);



    }

}

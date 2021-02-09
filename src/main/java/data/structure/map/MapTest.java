package data.structure.map;

import org.junit.Test;

import java.util.*;

public class MapTest {

    @Test
    public void test() {
        List list = new ArrayList();
        List arrayList = new ArrayList();

        arrayList.add(1);
        arrayList.add("app1");
        arrayList.add(-1);
        list.add(arrayList);
        arrayList.clear();
        arrayList.add(1);
        arrayList.add("app2");
        arrayList.add(-1);
        list.add(arrayList);

        list.forEach(System.out::println
        );

        Map<String, Integer> map = new HashMap<>();

        map.put("ser", 1);
        map.put("af24", 1231);
        map.put("aq", 1231);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
        for (Iterator<Map.Entry<String, Integer>> it = entries.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> next = it.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());


        }

    }


}

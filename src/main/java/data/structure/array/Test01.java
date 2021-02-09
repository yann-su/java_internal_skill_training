package data.structure.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test01 {


    @Test
    public void test() {
        int[] array = new int[10];
        int[] array1 = {10, 2, 3, 2, 4, 5, 2, 21};
        int[] array2 = new int[]{10, 20, 32};

        Object[] objects = {10, 231, "1231", true};

        List list = new ArrayList();
        list.add(1);
        list.add(10);
        list.add("1asdada4");

//        Arrays.stream(array1).forEach(System.out::print);

        Arrays.stream(objects).filter(x -> {

            if (x.equals(10)) {
                return false;
            }
            return true;

        }).forEach(System.out::println);


    }


}

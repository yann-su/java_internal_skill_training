package jvm.mock;

import java.util.ArrayList;
import java.util.List;

public class HeapMockOverError {

    public static void main(String[] args) {

        int i = 0;

        try {
            List list = new ArrayList();
            String s = "hello heap for mock";
            while (true){
                list.add(s);
                s = s + s;
                i ++ ;
            }
        }catch (OutOfMemoryError out){
            System.out.println(i);
            out.printStackTrace();
        }


    }

}

package jvm.mock;

import java.io.IOException;
import java.util.ArrayList;

/**
 * jmap.exe  -dump:format=b,live,file=1.bin [pid]21004
 */
public class LiveGcRoot {

    public static void main(String[] args) throws IOException {

        ArrayList<Object> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println(1);
        System.in.read();


        list = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end");


    }

}

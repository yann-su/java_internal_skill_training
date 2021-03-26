package jvm.mock;

import java.util.ArrayList;

//-Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
public class GcDemo {

    private final static int _512K = 512 * 1024;
    private final static int _1M = 1024 * 1024;
    private final static int _7M = 7 * _1M;
    private final static int _8M = 8 * _1M;
    private final static int _9M = 9 * _1M;
    private final static int _10M = 10 * _1M;


    public static void main(String[] args) throws InterruptedException {
//        String a = "a";

//        ArrayList<Object> list = new ArrayList<>();
//        list.add(new byte[_7M]);
        new GcDemo().test();
//        byte[] bytes = new byte[_7M];
//        byte[] bytes1 = new byte[_7M];
//        Thread.sleep(1000L);
        System.out.println(1);
//        Thread.sleep(100000L);

    }

    public void test(){
        byte[] bytes = new byte[_9M];
    }


}

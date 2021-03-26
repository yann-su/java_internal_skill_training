package jvm.mock;

public class HeapMapOfMem {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(1);
        Thread.sleep(30000);
        byte[] bytes = new byte[1024 * 1024* 10];
        System.out.println(2);
        Thread.sleep(30000);
        bytes = null;
        System.gc();
        System.out.println(3);
        Thread.sleep(100000L);






    }
}

package safety;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadShare extends Thread{


    final static AtomicInteger data = new AtomicInteger(0);
    //要是使用int类型，会出现指令问题,共享变量问题
//    static int data = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
            for (int i = 0;i < 11000;i++){
                data.getAndIncrement();
            }
        });

        Thread thread1 = new Thread(()->{
            for (int i = 0;i < 11000;i++){
                data.getAndDecrement();
            }
        });

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println(data.get());


    }

}

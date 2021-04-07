package base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRunnable implements Runnable{


    @Override
    public void run() {
        System.out.println("hello Runnable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        //交给底层cpu调度
        thread.start();

        log.debug("hello callable");


    }

}

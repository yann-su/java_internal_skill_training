package activity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//
public class DeadLockDemo {

    final static Object lock1 = new Object();
    final static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1){
                log.info("t1 get lock1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    log.info("t1 get lock2");
                }

            }

        },"t1").start();
        new Thread(()->{
            synchronized (lock2){
                log.info("t1 get lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    log.info("t1 get lock1");
                }

            }

        },"t2").start();





    }

}

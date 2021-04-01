package reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class interruptable {

    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(() -> {
            try {
                log.info("尝试获取锁");
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //没有获得锁
                return;
            }
            try {
                log.info("获得锁");
            } finally {
                reentrantLock.unlock();
            }

        }, "t1");


        //让主线程获取了锁
        reentrantLock.lock();
        Thread.sleep(1000);
        thread.start();
//        log.info("尝试打断锁");
        //如果不打断锁会无限制等待，打断锁可以使用锁
//        thread.interrupt();








    }


}

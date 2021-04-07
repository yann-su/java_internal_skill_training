package reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class TimeOut {

    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            log.info("be about to try lock");
            try {
                //尝试阻塞2s，也可以不尝试，也可以不添加参数
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    log.info("lock acquisition failed");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                log.info("lock acquisition success");
            }finally {
                lock.unlock();
            }

        }, "name");



        thread.start();

        lock.lock();

        Thread.sleep(1000);

        lock.unlock();


    }


}
